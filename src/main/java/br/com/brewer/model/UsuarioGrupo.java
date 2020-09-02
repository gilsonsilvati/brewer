package br.com.brewer.model;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;

@Entity
@Table(name = "usuario_grupo")
public class UsuarioGrupo {
	
	@EmbeddedId
	private UsuarioGrupoId id;

	public UsuarioGrupoId getId() {
		return id;
	}
	public void setId(UsuarioGrupoId id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		
		UsuarioGrupo other = (UsuarioGrupo) obj;
		return new EqualsBuilder().append(id, other.id).isEquals();
	}

}
