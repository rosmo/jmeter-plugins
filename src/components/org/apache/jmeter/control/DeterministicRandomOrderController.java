/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.jmeter.control;

import java.io.Serializable;
import java.util.Collections;
import java.util.Random;

import org.apache.jmeter.samplers.Sampler;
import org.apache.jmeter.testelement.property.JMeterProperty;
import org.apache.jmeter.testelement.property.StringProperty;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterThread;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

/**
 * A controller that runs its children each at most once, but in a random order.
 * You can specify a seed for the random generator, so that it will random, but
 * not so random that you will get different order each run time.
 *
 */
public class DeterministicRandomOrderController extends GenericController implements Serializable {
    private static final Logger log = LoggingManager.getLoggerForClass();

    private static final long serialVersionUID = 240L;

    private static final String SEED = "DeterministicRandomOrderController.seed"; // $NON-NLS-1$

    /**
     * Create a new DeterministicRandomOrderController.
     */
    public DeterministicRandomOrderController() {
    }

    /**
     * @see GenericController#initialize()
     */
    @Override
    public void initialize() {
        super.initialize();
        this.reorder();
    }

    /**
     * @see GenericController#reInitialize()
     */
    @Override
    protected void reInitialize() {
        super.reInitialize();
        this.reorder();
    }

    /**
     * Replace the subControllersAndSamplers list with a reordered ArrayList.
     */
    private void reorder() {
	long seed = new Integer(getSeed()).longValue();
	Random generator = new Random(seed);
        Collections.shuffle(subControllersAndSamplers, generator);
    }

    /**
     * @param string
     *            the seed to save
     */
    public void setSeed(String string) {
        if(log.isDebugEnabled()) {
            log.debug("setSeed(" + string + ")");
        }
        setProperty(new StringProperty(SEED, string));
    }

    /**
     * @return the seed
     */
    public String getSeed() {
        JMeterProperty prop=getProperty(SEED);
        prop.recoverRunningVersion(this);
        return prop.getStringValue();
    }

}
