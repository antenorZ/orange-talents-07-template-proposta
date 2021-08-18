package br.com.zup.propostas.enums;

public enum ResultadoSolicitacao{
	COM_RESTRICAO{
        public EstadoProposta getEstadoProposta(){
            return EstadoProposta.NAO_ELEGIVEL;
        }
    }, SEM_RESTRICAO{
    	public EstadoProposta getEstadoProposta(){
            return EstadoProposta.ELEGIVEL;
        }
    };
    public abstract EstadoProposta getEstadoProposta();
}
