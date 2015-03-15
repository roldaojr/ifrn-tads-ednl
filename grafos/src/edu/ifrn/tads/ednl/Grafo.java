package edu.ifrn.tads.ednl;
	
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
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

	public Vertice oposto(Vertice v, Aresta a) {
		for(Vertice ve: incidencia.keySet()) {
			if(v.equals(ve)) continue;
			Set<Aresta> as = incidencia.get(ve);
			if(as.contains(a)) {
				return ve;
			}
		}
		return null;
	}
	public boolean eAdjacente(Vertice v, Aresta w) {
		Set<Aresta> arestas;
		arestas = incidencia.getOrDefault(v, new HashSet<>());
		return arestas.contains(w);
	}
	public void substituir(Vertice v, Object x) {
		v.valor = x;
	}
	public void substituir(Aresta a, int x) {
		a.valor = x;
	}
	public Vertice inserirVertice(Object x) {
		Vertice v = new Vertice(x);
		incidencia.put(v, new HashSet<>());
		return v;
	}
	public Aresta inserirAresta(Vertice v, Vertice w, int x) {
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
	public Set<Vertice> verticesAdjacentes(Vertice v) {
		Set<Vertice> result = new HashSet<>();
		for(Aresta as: arestasIncidentes(v)) {
			result.add(oposto(v, as));
		}
		return result;
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
	public Map<Vertice, Integer> distanciaDfs(Vertice v) {
		// marcar todos como não visitado
		for(Vertice w: vertices()) {
			w.visitado = 0;
		}
		// Guardar as distancias
		Map<Vertice, Integer> distancias = new HashMap<>();
		distancias.put(v, 0);
		for(Vertice w: vertices()) {
			if(v.visitado == 0) {
				distancias.put(w, distanciaDfsVertices(v, w));
			}
		}
		return distancias;
	}
	private int distanciaDfsVertices(Vertice v, Vertice w) {
		v.visitado = -1;
		if(v.equals(w)) return 0;
		for(Aresta as: arestasIncidentes(v)) {
			Vertice o = oposto(v, as);
			if (o.visitado == 0) {
				return distanciaDfsVertices(o, w) + as.valor;
			}
		}
		return 0;
	}
	public Map<Vertice, Integer> distanciaBfs(Vertice v) {
		// marcar todos como não visitado
		for(Vertice x: vertices()) {
			x.visitado = 0;
		}
		v.visitado = -1;
		// Guardar as distancias
		Map<Vertice, Integer> distancias = new HashMap<>();
		distancias.put(v, 0);
		// criar fila e colocar o primeiro elemento
		Deque<Vertice> fila = new LinkedList<Vertice>();
		fila.add(v);
		// iterar sobre a fila
		while(!fila.isEmpty()) {
			// pegar o primeiro da fila
			Vertice x = fila.remove();
			// Pegar os vertices opostos
			for(Aresta as: arestasIncidentes(x)) {
				Vertice o = oposto(x, as);
				// adicionar vertices adjacentes não visitados a lista
				if(o.visitado != -1) {
					distancias.put(o, distancias.get(x)+as.valor);
					o.visitado = -1;
					fila.add(o);
				}
			}
			x.visitado = 1;
		}
		return distancias;
	}
}
