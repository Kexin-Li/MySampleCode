package com.hik.wizard;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class Welcome extends WizardPage{
	
	protected Welcome() {
		super(InstallWizard.WELCOME, "欢迎安装", ImageDescriptor.createFromFile(PageOne.class, "q.gif"));
        this.setMessage("欢迎使用tomcat安装工具");
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		
		// 欢迎页面
		new Label(composite, SWT.LEFT).setText("安装步骤：\r\n1. 解压tomcat\r\n2. 部署war包\r\n3. 启动tomcat");
		
		setControl(composite);
	}

}
