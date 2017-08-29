package com.hik.wizard;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class Thanks extends WizardPage {

	protected Thanks() {
		super(InstallWizard.THANKS, "感谢:",ImageDescriptor.createFromFile(PageOne.class, "q.gif"));  
        this.setMessage("感谢您的安装！");
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());
		new Label(composite, SWT.CENTER).setText("安装成功！");
		setControl(composite);
	}

}
