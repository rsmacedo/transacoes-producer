package br.edu.utfpr.td.cotsi.exchange.producer;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	private Queue filaTransacoes;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private AmqpAdmin amqpAdmin;

	private FanoutExchange fanout = new FanoutExchange("transacoes.financeiras", true, false);

	/*
	@PostConstruct
	public void criarFila() {
		filaTransacoes = new Queue("transacoes.suspeitas", true);
		amqpAdmin.declareQueue(filaTransacoes);
		//String msg = String.format("Processando transacao suspeita");
		//processarArquivotransacoes();
		//rabbitTemplate.convertAndSend(fanout.getName(), "", msg);
	}
	*/

	/*public void processarArquivotransacoes() {
		List<br.edu.utfpr.td.cotsi.modelo.Transacao> transacoes = new utils.LeitorArquivo().lerArquivo();
		for (br.edu.utfpr.td.cotsi.modelo.Transacao transacao : transacoes) {
			rabbitTemplate.convertAndSend(this.filaTransacoes.getName(), transacao.toString());
		}
	}*/
}
