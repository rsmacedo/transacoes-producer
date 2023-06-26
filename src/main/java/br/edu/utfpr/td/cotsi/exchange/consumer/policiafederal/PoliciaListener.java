package br.edu.utfpr.td.cotsi.exchange.consumer.policiafederal;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.edu.utfpr.td.cotsi.modelo.Transacao;

@Component
public class PoliciaListener {

	@RabbitListener(queues = "policia.federal")
	public void listen (Transacao t) {
		System.out.println(t);
	}

}
