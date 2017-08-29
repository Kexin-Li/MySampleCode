package com.hik.wizard;

import org.eclipse.jface.wizard.Wizard;

public class InstallWizard extends Wizard {
	
	public static final String WELCOME = "WELCOME";
	public static final String P1 = "PAGE_1";
	public static final String P2 = "PAGE_2";
	public static final String P3 = "PAGE_3";
	public static final String THANKS = "THANKS";
	
	private Welcome welcome;
	private PageOne one;
	private PageTwo two;
	private PageThree three;
	private Thanks thanks;

	public InstallWizard() {
		welcome = new Welcome();
		one = new PageOne();
		two = new PageTwo();
		three = new PageThree();
		thanks = new Thanks();
		
		this.addPage(welcome);
		this.addPage(one);
		this.addPage(two);
		this.addPage(three);
		this.addPage(thanks);
		this.setWindowTitle("安装工具向导");
	}
	
	public boolean canFinish() {
		if((this.getContainer().getCurrentPage() == thanks))
			return true;
		else
			return false;
	}

	@Override
	public boolean performFinish() {
		return true;
	}
	
}
