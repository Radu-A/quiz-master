package com.github.Radu_A.evaluacion_final.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@DiscriminatorValue("SELECCION_MULTIPLE")
@Getter
@Setter
public class PreguntaSeleccionMultiple extends Pregunta {

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "opciones", joinColumns = @JoinColumn(name = "pregunta_id"))
	@Column(name = "opcion")
	private List<String> opciones;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "respuestas_correctas", joinColumns = @JoinColumn(name = "pregunta_id"))
	@Column(name = "respuesta_correcta")
	private List<String> opcionesCorrectas;
}
