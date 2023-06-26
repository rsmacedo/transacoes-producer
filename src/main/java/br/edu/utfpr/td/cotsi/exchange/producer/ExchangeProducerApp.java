package br.edu.utfpr.td.cotsi.exchange.producer;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("br.edu.utfpr.td.cotsi.transacoes.producer")
public class ExchangeProducerApp {

	@Autowired
	private AmqpAdmin amqpAdmin;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ExchangeProducerApp.class, args);
	}

	@PostConstruct
	public void configuraCanais() {
		Queue receitaFederal = new Queue("receita.federal",true);
		amqpAdmin.declareQueue(receitaFederal);
		
		Queue policiaFederal = new Queue("policia.federal",true);
		amqpAdmin.declareQueue(policiaFederal);
		
		FanoutExchange fanout = new FanoutExchange("transacoes.suspeitas", true, false);
		amqpAdmin.declareExchange(fanout);
		Binding binding = BindingBuilder.bind(receitaFederal).to(fanout);
		amqpAdmin.declareBinding(binding);
		binding = BindingBuilder.bind(policiaFederal).to(fanout);
		amqpAdmin.declareBinding(binding);
		
	}
}
