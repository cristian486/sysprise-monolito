package br.com.sysprise.model.venda;

import br.com.sysprise.infra.exception.ValidacaoException;

public enum StatusVenda {

    ABERTO("Aberto") {
        @Override
        public void aprovar(Venda venda) {
            venda.atualizarStatus(AGUARDANDO_APROVACAO);
        }

        @Override
        public void cancelar(Venda venda) {
            venda.atualizarStatus(CANCELADO);
        }
    },
    AGUARDANDO_APROVACAO("Aguardando Aprovação") {

        @Override
        public void aprovar(Venda venda) {
            venda.atualizarStatus(PROCESSAMENTO);
        }

        @Override
        public void cancelar(Venda venda) {
            venda.atualizarStatus(CANCELADO);
        }
    }, CANCELADO("Cancelado"), FINALIZADO("Finalizado"),
    PROCESSAMENTO("Processamento") {

        @Override
        public void aprovar(Venda venda) {
            venda.atualizarStatus(FINALIZADO);
        }

        @Override
        public void cancelar(Venda venda) {
            venda.atualizarStatus(CANCELADO);
        }
    };

    private final String nome;

    StatusVenda(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

    public void aprovar(Venda venda) {
        throw new ValidacaoException("Não é possível mudar o status da venda!");
    }

    public void cancelar(Venda venda) {
        throw new ValidacaoException("Não é possível mudar o status da venda!");
    }
}
