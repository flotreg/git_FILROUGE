/*
 * Copyright (C) 2008-2010 Martin Riesz <riesz.martin at gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.pneditor.editor.actions.edit;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import org.pneditor.editor.Root;
import org.pneditor.editor.commands.CutCommand;
import org.pneditor.util.GraphicsTools;

/**
 *
 * @author Martin Riesz <riesz.martin at gmail.com>
 */
@SuppressWarnings("serial")
public class CutAction extends AbstractAction {

    private final Root root;

    public CutAction(final Root root) {
    	super();
        this.root = root;
        String name = "Cut";
        putValue(NAME, name);
        putValue(SMALL_ICON, GraphicsTools.getIcon("pneditor/Cut16.gif"));
        putValue(SHORT_DESCRIPTION, name);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl X"));
        setEnabled(false);
    }

    @Override
	public void actionPerformed(final ActionEvent e) {
        this.root.getClipboard().setContents(this.root.getSelectedElementsWithClickedElement());
        this.root.getUndoManager().executeCommand(new CutCommand(this.root.getSelectedElementsWithClickedElement(),this.root.getGraphicPetriNet()));
        this.root.getSelection().clear();
        this.root.setClickedElement(null);
        this.root.refreshAll();
    }
}
