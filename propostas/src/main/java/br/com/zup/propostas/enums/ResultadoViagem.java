package br.com.zup.propostas.enums;

public enum ResultadoViagem {
    CRIADO{
        public EstadoViagem getEstadoViagem(){
            return EstadoViagem.CRIADO;
        }
    },
    FALHA{
        public EstadoViagem getEstadoViagem(){
            return EstadoViagem.FALHA;
        }
    };
    public abstract EstadoViagem getEstadoViagem();
}
