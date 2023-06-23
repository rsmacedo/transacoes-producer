package br.edu.utfpr.td.cotsi.exchange.consumer.receitafederal;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.amqp.core.Queue;

@SpringBootApplication
@ComponentScan("br.edu.utfpr.td.cotsi.exchange")
public class ExchangeConsumerApp {

	@Autowired
	private AmqpAdmin amqpAdmin;

	public static void main(String[] args) {
		SpringApplication.run(ExchangeConsumerApp.class, args);
	}

	@PostConstruct
	public void configurarCanais() {
		Queue receitaFederal = new Queue("receita.federal", true);
		amqpAdmin.declareQueue(receitaFederal);
	}

}
