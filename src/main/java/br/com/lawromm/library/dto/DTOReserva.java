package br.com.lawromm.library.dto;

import br.com.lawromm.library.dto.account.AccountDtoSummary;
import br.com.lawromm.library.dto.work.WorkDtoSummary;
import br.com.lawromm.library.model.Reserva;
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
public class DTOReserva {
  private Long codigo;
  private AccountDtoSummary account;
  private WorkDtoSummary obra;
  private LocalDateTime dataReserva;
  private LocalDateTime dataRetirada;

  public DTOReserva(Reserva reserva) {
    this(
      reserva.getCodigo(),
      new AccountDtoSummary(reserva.getUsuario()),
      new WorkDtoSummary(reserva.getWork()),
      reserva.getDataReserva(),
      reserva.getDataRetirada()
    );
  }

  public static List<DTOReserva> converter(List<Reserva> list) {
    return list.stream().map(DTOReserva::new).collect(Collectors.toList());
  }
}
