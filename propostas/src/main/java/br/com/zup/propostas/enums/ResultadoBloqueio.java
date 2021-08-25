package br.com.zup.propostas.enums;

public enum ResultadoBloqueio {
    FALHA{
        public EstadoBloqueio getEstadoBloqueio(){
            return EstadoBloqueio.FALHA;
        }
    },
    BLOQUEADO{
        public EstadoBloqueio getEstadoBloqueio(){
            return EstadoBloqueio.BLOQUEADO;
        }
    };
    public abstract EstadoBloqueio getEstadoBloqueio();
}
