package br.edu.utfpr.td.cotsi.transacoes.consumer;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Listener {
	
	FanoutExchange fanout = new FanoutExchange("transacoes.suspeitas", true, false);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@RabbitListener(queues = "transacoes.financeiras")
	public void listen(String in) {
	    processarTransacao(in);
	}
	
	public void processarTransacao(String in) {
		try
		{
		    Thread.sleep(10);
		    System.out.println(in);
		    if(identificarTransacaoSuspeita(in)) {
		    	String msg = String.format("Processando transacao suspeita");
				rabbitTemplate.convertAndSend(fanout.getName(), msg, in);
		    }
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		
	}
	
	public boolean identificarTransacaoSuspeita(String transacao) {
		String numeroString = "";
		Double numero;
		String valor = "valor=";

		int indice = transacao.indexOf(valor);
		
		String parte = transacao.substring(indice+6, indice+20);

		for (int i = 0; i < parte.length(); i++) {
		    char caractere = parte.charAt(i);
		    if (caractere == ',') {
		        break;
		    }
		    numeroString = numeroString + caractere;
		}
		numero = Double.parseDouble(numeroString);
	
		if(numero >= 40000) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	
}


