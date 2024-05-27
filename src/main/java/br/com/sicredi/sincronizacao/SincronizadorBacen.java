package br.com.sicredi.sincronizacao;

import br.com.sicredi.sincronizacao.component.ParameterComponent;
import br.com.sicredi.sincronizacao.service.SincronizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SincronizadorBacen implements CommandLineRunner {

  @Autowired
  private SincronizacaoService sincronizacaoService;

  public static void main(String[] args) {
    SpringApplication.run(SincronizadorBacen.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    if (args != null && args.length > 0) {
      ParameterComponent.setFilePath(args[0]);
      sincronizacaoService.syncAccounts();
    }
  }

}