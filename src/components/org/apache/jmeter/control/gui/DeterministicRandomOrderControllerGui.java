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
package org.apache.jmeter.control.gui;

import org.apache.jmeter.control.DeterministicRandomOrderController;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.util.JMeterUtils;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * GUI for DeterministicRandomOrderController.
 *
 */
public class DeterministicRandomOrderControllerGui extends LogicControllerGui {

    private static final long serialVersionUID = 240L;

    /**
     * A field allowing the user to specify the random seed.
     */
    private JTextField theSeed;

    /** The name of the condition field component. */
    private static final String SEED = "Random_Seed"; // $NON-NLS-1$

    private static final String SEED_LABEL = "deterministic_random_order_seed_label"; // $NON-NLS-1$
    
    @Override
    public String getLabelResource() {
        return "deterministic_random_order_control_title"; // $NON-NLS-1$
    }

    @Override
    public String getStaticLabel()
    {
	return "Deterministic Random Order Controller";
    }
    
    public DeterministicRandomOrderControllerGui() {
	init();
    }
    /**
     * A newly created component can be initialized with the contents of a Test
     * Element object by calling this method. The component is responsible for
     * querying the Test Element object for the relevant information to display
     * in its GUI.
     *
     * @param element
     *            the TestElement to configure
     */
    @Override
    public void configure(TestElement element) {
        super.configure(element);
        if (element instanceof DeterministicRandomOrderController) {
            theSeed.setText(((DeterministicRandomOrderController) element).getSeed());
        }

    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public TestElement createTestElement() {
        DeterministicRandomOrderController ic = new DeterministicRandomOrderController();
        modifyTestElement(ic);
        return ic;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void modifyTestElement(TestElement ic) {
        configureTestElement(ic);
	if (ic instanceof DeterministicRandomOrderController) {
            if (theSeed != null && theSeed.getText().length() > 0) {
                ((DeterministicRandomOrderController) ic).setSeed(theSeed.getText());
            } else {
                ((DeterministicRandomOrderController) ic).setSeed("0"); // $NON-NLS-1$
            }
        }

    }

    /**
     * Implements JMeterGUIComponent.clearGui
     */
    @Override
    public void clearGui() {
        super.clearGui();
        theSeed.setText("0"); // $NON-NLS-1$
    }

    /**
     * Initialize the GUI components and layout for this component.
     */
    private void init() {
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());
        add(makeTitlePanel(), BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createConditionPanel(), BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Create a GUI panel containing the condition. TODO make use of the field
     *
     * @return a GUI panel containing the condition components
     */
    private JPanel createConditionPanel() {
        JPanel conditionPanel = new JPanel(new BorderLayout(5, 0));

        // Seed LABEL
        JLabel seedLabel = new JLabel("Random order seed:");
        conditionPanel.add(seedLabel, BorderLayout.WEST);

        // TEXT FIELD
        // This means exit if last sample failed
        theSeed = new JTextField("0");  // $NON-NLS-1$
        theSeed.setName(SEED);
        seedLabel.setLabelFor(theSeed);
        conditionPanel.add(theSeed, BorderLayout.CENTER);
        
        conditionPanel.add(Box.createHorizontalStrut(seedLabel.getPreferredSize().width
                + theSeed.getPreferredSize().width), BorderLayout.NORTH);

        return conditionPanel;
    }



}
