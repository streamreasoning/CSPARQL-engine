/*
 * @(#)Snippet.java   1.0   24/set/2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id$
 */
package eu.larkc.csparql.sparql.sesame;

import java.util.ArrayList;
import java.util.HashMap;

import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.impl.StatementImpl;
import org.openrdf.query.BindingSet;
import org.openrdf.query.GraphQuery;
import org.openrdf.query.GraphQueryResult;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.Query;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.memory.MemoryStore;
import org.openrdf.sail.memory.model.MemURI;

import eu.larkc.csparql.common.RDFTable;
import eu.larkc.csparql.common.RDFTuple;
import eu.larkc.csparql.sparql.api.SparqlEngine;
import eu.larkc.csparql.sparql.api.SparqlQuery;

public class SesameEngine implements SparqlEngine {

	private SailRepository repository = null;
	private RepositoryConnection connection = null;
	private HashMap<String, Query> queries = null;


	public void destroy() {
		if (this.repository == null) {
			return;
		}
		try {
			this.repository.shutDown();
		} catch (final RepositoryException e) {
			e.printStackTrace();
		}
		this.repository = null;
	}

	public GraphQueryResult evaluateGraphQuery(final String query) {
		GraphQuery q = null;

		q = (GraphQuery) this.createOrGetQuery(query);
		// final long l = this.connection.size((Resource) null);

		GraphQueryResult r = null;

		try {

			if (q != null) {
				r = q.evaluate();

				return r;
			}

		} catch (final QueryEvaluationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public RDFTable evaluateQuery(final SparqlQuery query) {

		Query q = null;

		q = this.createOrGetQuery(query.getQueryCommand());

		try {

			if (q != null) {

				if (q instanceof TupleQuery) {
					final TupleQuery tq = (TupleQuery) q;
					final TupleQueryResult tqr = tq.evaluate();

					final RDFTable table = new RDFTable(tqr.getBindingNames());

					while (tqr.hasNext()) {

						final RDFTuple t = new RDFTuple();

						final BindingSet s = tqr.next();
						for (final String n : tqr.getBindingNames()) {
							t.addFields(s.getBinding(n).getValue().stringValue());
						}

						table.add(t);
					}

					return table;

				} else if (q instanceof GraphQuery) {

					final ArrayList<String> names = new ArrayList<String>();

					names.add("Subject");
					names.add("Predicate");
					names.add("Object");

					final GraphQuery gq = (GraphQuery) q;

					final GraphQueryResult gqr = gq.evaluate();

					final RDFTable table = new RDFTable(names);

					while (gqr.hasNext()) {

						final RDFTuple t = new RDFTuple();

						final Statement s = gqr.next();

						t.addFields(s.getSubject().stringValue(), s.getPredicate().stringValue(),
								s.getObject().stringValue());

						table.add(t);
					}

					return table;
				}

				return null;
			}

		} catch (final QueryEvaluationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private Query createOrGetQuery(final String query) {

		if (!this.queries.containsKey(query)) {
			try {
				this.queries.put(query, this.connection.prepareTupleQuery(QueryLanguage.SPARQL,
						query));
			} catch (final RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (final MalformedQueryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return this.queries.get(query);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addStatement(final String subject, final String predicate, final String object) {
		final Statement s = this.createStatement(subject, predicate, object);

		try {
			this.connection.add(s, (Resource) null);
		} catch (final RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Statement createStatement(final String subject, final String predicate,
			final String object) {
		final MemURI s = new MemURI("this", "", subject);
		final MemURI p = new MemURI("this", "", predicate);
		final MemURI o = new MemURI("this", "", object);
		return new StatementImpl(s, p, o);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addStatement(final String subject, final String predicate,
			final String object, final String timestamp) {

	}


	public void initialize() {
		// TODO: add initialization phase

		if (this.repository != null) {
			return;
		}

		this.queries = new HashMap<String, Query>();

		this.repository = new SailRepository(new MemoryStore());
		try {
			this.repository.initialize();
			this.connection = this.repository.getConnection();
		} catch (final RepositoryException e) {
			e.printStackTrace();
		}
	}

	public void clean() {

		try {
			this.connection.close();
			this.repository = null;
		} catch (final RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.initialize();

	}

	public void cleanWtf() {
		try {
			// System.out.println("prima: " + this.connection.size((Resource) null));
			this.connection.clear((Resource) null);
			// System.out.println("dopo: " + this.connection.size((Resource) null));
		} catch (final RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void addStatement(String subject, String predicate, String object,
			long timestamp) {
		throw new UnsupportedOperationException();

	}

	public String getEngineType() {
		return "sesame";
	}

	@Override
	public void execUpdateQueryOverDatasource(String queryBody) {
		
	}

	@Override
	public void activateInference() {
		
	}

	@Override
	public void setInferenceRulesFilePath(String path) {
		// TODO Auto-generated method stub
		
	}
}
