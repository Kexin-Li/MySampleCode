package com.hik;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.hik.wizard.InstallWizard;

public class WizardTest extends ApplicationWindow {
	
	public WizardTest() {
		super(null);
	}
	
	protected Control createContents(Composite parent) {
		WizardDialog dlg = new WizardDialog(Display.getCurrent().getActiveShell(), new InstallWizard());
		dlg.open();
		
		return parent;
	}
	
	public static void main(String[] args) {
		WizardTest test = new WizardTest();
		test.open();
		Display.getCurrent().dispose();
	}

}
