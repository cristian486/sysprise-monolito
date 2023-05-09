package br.com.sysprise.model.compra;

import br.com.sysprise.infra.exception.ValidacaoException;

public enum StatusCompra {
    AGUARDANDO_APROVACAO("Aguardando Aprovação") {
        @Override
        public void aprovar(Compra compra) {
            compra.atualizarStatus(AGUARDANDO_RECEBIMENTO);
        }

        @Override
        public void cancelar(Compra compra) {
            compra.atualizarStatus(CANCELADO);
        }
    }, AGUARDANDO_RECEBIMENTO("Aguardando Recebimento") {
        @Override
        public void aprovar(Compra compra) {
            compra.atualizarStatus(CONFERENCIA);
        }

        @Override
        public void cancelar(Compra compra) {
            compra.atualizarStatus(CANCELADO);
        }
    }, CONFERENCIA("Conferência") {
        @Override
        public void aprovar(Compra compra) {
            compra.atualizarStatus(FINALIZADO);
        }

        @Override
        public void cancelar(Compra compra) {
            compra.atualizarStatus(DIVERGENCIAS);
        }
    }, DIVERGENCIAS("Divergência"), CANCELADO("Cancelado"), FINALIZADO("Finalizado");

    private final String nome;

    StatusCompra(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

    public void aprovar(Compra compra) {
        throw new ValidacaoException("Não é possível mudar o status da compra");
    }

    public void cancelar(Compra compra) {
        throw new ValidacaoException("Não é possível mudar o status da compra");
    }
}
