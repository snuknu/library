package br.com.lawromm.library.model;

import br.com.lawromm.library.dto.DTOExemplar;
import br.com.lawromm.library.enumeration.SituacaoExemplar;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@IdClass(IdExemplar.class)
public class Exemplar {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "num_Exemplar")
  private Long numExemplar;

  @Id
  @Column(name = "cod_obra")
  private Long obraCodigo;

  private LocalDateTime dataAquisicao;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "situacao_exemplar")
  private SituacaoExemplar situacao;

  @ManyToOne
  @JoinColumn(name = "cod_obra", insertable = false, updatable = false)
  private Work obra;

  public Exemplar(DTOExemplar dto) {
    this(
      dto.getNumExemplar(),
      dto.getObraCodigo(),
      dto.getDataAquisicao(),
      dto.getSituacao(),
      null
    );
  }

  public List<Exemplar> converter(List<DTOExemplar> dtoList) {
    return dtoList.stream().map(Exemplar::new).collect(Collectors.toList());
  }
}
