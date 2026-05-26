package com.github.Radu_A.evaluacion_final.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@DiscriminatorValue("SELECCION_UNICA")
@Getter
@Setter
public class PreguntaSeleccionUnica extends Pregunta {

	// Podrías guardar las opciones como una lista de Strings en otra tabla
	// intermedia
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "opciones", joinColumns = @JoinColumn(name = "pregunta_id"))
	@Column(name = "opcion")
	private List<String> opciones;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "respuestas_correctas", joinColumns = @JoinColumn(name = "pregunta_id"))
	@Column(name = "respuesta_correcta")
	private List<String> opcionesCorrectas;
}
