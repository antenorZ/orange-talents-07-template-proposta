package br.com.zup.propostas.enums;

public enum ResultadoCarteira {
    ASSOCIADA{
        public EstadoCarteira getEstadoCarteira(){
            return EstadoCarteira.ASSOCIADA;
        }
    }, FALHA{
        public EstadoCarteira getEstadoCarteira(){
            return EstadoCarteira.FALHA;
        }
    };
    public abstract EstadoCarteira getEstadoCarteira();
}
