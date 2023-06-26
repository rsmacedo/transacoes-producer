package br.edu.utfpr.td.cotsi.transacoes.consumer;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.cotsi.modelo.Transacao;

@Component
public class Listener {
	
	FanoutExchange fanout = new FanoutExchange("transacoes.suspeitas", true, false);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@RabbitListener(queues = "transacoes.financeiras")
	public void listen(Transacao t) {
	    processarTransacao(t);
	}
	
	public void processarTransacao(Transacao t) {
		try
		{
		    Thread.sleep(10);
		    System.out.println(t);
		    if(identificarTransacaoSuspeita(t)) {
		    	String msg = String.format("Processando transacao suspeita");
				rabbitTemplate.convertAndSend(fanout.getName(), msg, t);
		    }
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		
	}
	
	public boolean identificarTransacaoSuspeita(Transacao transacao) {
		
	
		if(transacao.getValor() >= 40000) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	
}


