/*
 * @(#)TransparentReasoner.java   1.0   05/ott/2009
 *
 * Copyright 2009-2009 Politecnico di Milano. All Rights Reserved.
 *
 * This software is the proprietary information of Politecnico di Milano.
 * Use is subject to license terms.
 *
 * @(#) $Id$
 */
package eu.larkc.csparql.core.engine;

import eu.larkc.csparql.cep.api.RdfSnapshot;

public class TransparentReasoner implements Reasoner {

   public RdfSnapshot augment(final RdfSnapshot snapshot) {
      return snapshot;
   }

}
