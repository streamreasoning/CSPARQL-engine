/**
 * Copyright 2011-2015 DEIB - Politecnico di Milano
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Acknowledgements:
 * We would like to thank Davide Barbieri, Emanuele Della Valle,
 * Marco Balduini, Soheila Dehghanzadeh, Shen Gao, and
 * Daniele Dell'Aglio for the effort in the development of the software.
 *
 * This work is partially supported by
 * - the European LarKC project (FP7-215535) of DEIB, Politecnico di
 * Milano
 * - the ERC grant “Search Computing” awarded to prof. Stefano Ceri
 * - the European ModaClouds project (FP7-ICT-2011-8-318484) of DEIB,
 * Politecnico di Milano
 * - the IBM Faculty Award 2013 grated to prof. Emanuele Della Valle;
 * - the City Data Fusion for Event Management 2013 project funded
 * by EIT Digital of DEIB, Politecnico di Milano
 * - the Dynamic and Distributed Information Systems Group of the
 * University of Zurich;
 * - INSIGHT NUIG and Science Foundation Ireland (SFI) under grant
 * No. SFI/12/RC/2289
 */
package eu.larkc.csparql.cep.esper;

import java.util.concurrent.ArrayBlockingQueue;

import eu.larkc.csparql.cep.api.RdfQuadruple;

public class EsperInjecter implements Runnable {

	private ArrayBlockingQueue<RdfQuadruple> queue;
	private EsperEngine engine;
	private RdfQuadruple tempQuadruple;

	private long lastQuadrupleTS = 0;
	private long TSDiff = 0;
	private long controlTSDiff = 0;
	private boolean firstElement = true;

	public EsperInjecter(ArrayBlockingQueue<RdfQuadruple> queue, EsperEngine engine) {
		super();
		this.queue = queue;
		this.engine = engine;
	}

	public void run() {
		while(true){
			synchronized(queue){
				tempQuadruple = queue.poll();
			}
			if(tempQuadruple != null){
				//La prima quadrupla deve far riferimento al tempo attuale di sistema per capire quando essere iniettata
				if(firstElement){
					controlTSDiff = tempQuadruple.getTimestamp() - System.currentTimeMillis();
					if(controlTSDiff <= 0){
						printQuadruple(tempQuadruple);
						engine.getEpService().getEPRuntime().sendEvent(tempQuadruple);
						lastQuadrupleTS = tempQuadruple.getTimestamp();
					} else {
						try {
							Thread.sleep(controlTSDiff);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						printQuadruple(tempQuadruple);
						engine.getEpService().getEPRuntime().sendEvent(tempQuadruple);
						lastQuadrupleTS = tempQuadruple.getTimestamp();
					}
					firstElement = false;
				} else {
					
					/*Dalla seconda quadrupla in il sistema deve capire se la sleep tra una quadrupla e l'altra ??? stata gi??? effettuata a monte per evitare di farla
					 * due volte. 
					 * Controllo se il TS della quadrupla attuale coincide, o ??? di poco precedente, al tempo attuale di sistema.
					 * Se risulta in linea con il tempo attuale di sistema la inserisco subito, altrimenti faccio il controllo con la quadrupla precendente e inserisco
					 * l'eventuale tempo di attesa
					*/
					if(tempQuadruple.getTimestamp() - System.currentTimeMillis() <= 0){
						printQuadruple(tempQuadruple);
						engine.getEpService().getEPRuntime().sendEvent(tempQuadruple);
						lastQuadrupleTS = tempQuadruple.getTimestamp();
					} else {

						TSDiff = tempQuadruple.getTimestamp() - lastQuadrupleTS;

						if(TSDiff < 0){
							System.out.println();
							System.out.println("Quadruple Timestamp ERROR , " + tempQuadruple.toString());
							System.out.println();
						} else if(TSDiff > 0){
							try {
								Thread.sleep(TSDiff);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							printQuadruple(tempQuadruple);
							engine.getEpService().getEPRuntime().sendEvent(tempQuadruple);
						} else{
							printQuadruple(tempQuadruple);
							engine.getEpService().getEPRuntime().sendEvent(tempQuadruple);
						}
						lastQuadrupleTS = tempQuadruple.getTimestamp();
					}
				}
			}
		}
	}

	private void printQuadruple(RdfQuadruple quad){
//		System.out.println(quad + " , " + System.currentTimeMillis());
	}
}
