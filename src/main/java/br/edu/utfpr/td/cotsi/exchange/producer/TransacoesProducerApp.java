package br.edu.utfpr.td.cotsi.exchange.producer;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import br.edu.utfpr.td.cotsi.modelo.Transacao;

@SpringBootApplication
@ComponentScan("br.edu.utfpr.td.cotsi.transacoes.producer")
public class TransacoesProducerApp {

	@Autowired
	private AmqpAdmin amqpAdmin;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	private Queue filaTransacoes;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TransacoesProducerApp.class, args);
	}

	@PostConstruct
	public void criarFila() {
		filaTransacoes = new Queue("transacoes.financeiras", true);
		amqpAdmin.declareQueue(filaTransacoes);
		processarArquivotransacoes();
	}

	public void processarArquivotransacoes() {
		List<Transacao> transacoes = new LeitorArquivo().lerArquivo();
		for (Transacao transacao : transacoes) {
			rabbitTemplate.convertAndSend(this.filaTransacoes.getName(), transacao.toString());
		}
	}
}
