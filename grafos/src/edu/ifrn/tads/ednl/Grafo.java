package edu.ifrn.tads.ednl;
	
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Grafo {
	private Map<Vertice, Set<Aresta>> incidencia;
	
	public Grafo() {
		incidencia = new HashMap<>();
	}
	public Set<Vertice> finalVertices(Aresta a) {
		Set<Vertice> ves = new HashSet<>();
		for(Vertice v: incidencia.keySet()) {
			Set<Aresta> as = incidencia.get(v);
			if(as.contains(a)) {
				ves.add(v);
			}
		}
		return ves;
	}
	public Vertice oposto(Vertice v, Aresta a) throws Exception {
		for(Vertice ve: incidencia.keySet()) {
			if(v.equals(ve)) continue;
			Set<Aresta> as = incidencia.get(ve);
			if(as.contains(a)) {
				return ve;
			}
		}
		throw new Exception("Não foi possivel encontrar oposto de vertice");
	}
	public boolean eAdjacente(Vertice v, Aresta w) {
		Set<Aresta> arestas;
		arestas = incidencia.getOrDefault(v, new HashSet<>());
		return arestas.contains(w);
	}
	public void substituir(Vertice v, Object x) {
		v.valor = x;
	}
	public void substituir(Aresta a, Object x) {
		a.valor = x;
	}
	public Vertice inserirVertice(Object x) {
		Vertice v = new Vertice(x);
		incidencia.put(v, new HashSet<>());
		return v;
	}
	public Aresta inserirAresta(Vertice v, Vertice w, Object x) {
		Aresta a = new Aresta(x);
		incidencia.get(v).add(a);
		incidencia.get(w).add(a);
		return a;
	}
	public Object removeVertice(Vertice v) {
		incidencia.remove(v);
		return v;
	}
	public Object removeAresta(Aresta a) {
		for(Set<Aresta> as: incidencia.values()) {
			as.remove(a);
		}
		return a.valor;
	}
	public Set<Aresta> arestasIncidentes(Vertice v) {
		return incidencia.get(v);
	}
	public Set<Vertice> vertices() {
		return incidencia.keySet();
	}
	public Set<Aresta> arestas() {
		Set<Aresta> arestas = new HashSet<>();
		for(Set<Aresta> as: incidencia.values()) {
			arestas.addAll(as);
		}
		return arestas;
	}
}
