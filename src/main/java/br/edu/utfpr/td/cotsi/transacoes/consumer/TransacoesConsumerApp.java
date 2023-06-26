package br.edu.utfpr.td.cotsi.transacoes.consumer;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.edu.utfpr.td.cotsi.transacoes.consumer")
public class TransacoesConsumerApp {

	@Autowired
	private AmqpAdmin amqpAdmin;

	private Queue filaTransacoes;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TransacoesConsumerApp.class, args);
	}

	@PostConstruct
	public void criarFila() {
		filaTransacoes = new Queue("transacoes.financeiras", true);
		amqpAdmin.declareQueue(filaTransacoes);		
	}

	
}
