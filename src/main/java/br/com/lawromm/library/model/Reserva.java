package br.com.lawromm.library.model;

import br.com.lawromm.library.dto.DTOReserva;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Reserva {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cod_reserva")
  private Long codigo;

  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account usuario;

  @ManyToOne
  @JoinColumn(name = "work_id")
  private Work work;

  private LocalDateTime dataReserva;

  private LocalDateTime dataRetirada;

  public Reserva(DTOReserva dto) {
    this(
      dto.getCodigo(),
      new Account(dto.getAccount().getId()),
      new Work(dto.getObra().getId()),
      dto.getDataReserva(),
      dto.getDataRetirada()
    );
  }

  public static List<Reserva> converter(List<DTOReserva> list) {
    return list.stream().map(Reserva::new).collect(Collectors.toList());
  }
}
