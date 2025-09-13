package br.ufal.ic.p2.wepayu.Interface;

import br.ufal.ic.p2.wepayu.Models.Empregado.Empregado;
import br.ufal.ic.p2.wepayu.Models.Empregado.EmpregadoComissionado;

public interface EmpregadoInterface {
    public void ajustaSalario(Double salario);
    public EmpregadoComissionado converteEmpregado(Empregado empregado, Double comissao) throws Exception;
}
