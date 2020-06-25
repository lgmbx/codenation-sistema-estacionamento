package challenge;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    final Integer totalVagas = 10;

    public List<Carro> carroList = new ArrayList<>();

    public void estacionar(Carro carro) {
        if(carrosEstacionados() < totalVagas){
            if(carro.getMotorista() == null){
                throw new EstacionamentoException("Motorista nao informado!");
            }
            if(carro.getMotorista().getIdade() < 18){
                throw new EstacionamentoException("Motorista menor de idade");
            }
            if(carro.getMotorista().getPontos() > 20){
                throw new EstacionamentoException("Motorista com habilitação suspensa");
            }
            carroList.add(carro);
        }
        if(carrosEstacionados() == totalVagas){
            Carro sair = carroList.stream()
                    .filter(x -> x.getMotorista().getIdade() < 55)
                    .findFirst()
                    .orElseThrow(() -> new EstacionamentoException("todos com mais de 55 anos"));

            carroList.remove(sair);
            carroList.add(carro);
        }
    }

    public int carrosEstacionados() {
        return carroList.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return carroList.contains(carro);
    }
}
