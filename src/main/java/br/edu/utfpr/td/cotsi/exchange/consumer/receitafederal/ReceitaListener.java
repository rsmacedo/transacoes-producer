package br.edu.utfpr.td.cotsi.exchange.consumer.receitafederal;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import br.edu.utfpr.td.cotsi.modelo.Transacao;


@Component
public class ReceitaListener {

	@RabbitListener(queues = "receita.federal")
	public void listen (Transacao t) {
		System.out.println("Processando transação suspeita: " + t);
	}

}
