package com.hik.wizard;

import org.eclipse.jface.dialogs.IPageChangingListener;
import org.eclipse.jface.dialogs.PageChangingEvent;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.hik.util.UnzipDemo;

public class PageOne extends WizardPage {
	
	protected PageOne() {
		super(InstallWizard.P1, "步骤一：解压tomcat", ImageDescriptor.createFromFile(PageOne.class, "q.gif"));
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		
		// 定义next事件
		WizardDialog dialog = (WizardDialog) getContainer();
		dialog.addPageChangingListener(new IPageChangingListener() {
			@Override
			public void handlePageChanging(PageChangingEvent e1) {
				if ((e1.getCurrentPage() instanceof Welcome) && (e1.getTargetPage() instanceof PageOne)) {
					decompress();
					setMessage("解压成功！");
				} else {
					setMessage("解压失败！");
				}
			}
		});
		setControl(composite);
	}
	
	// 解压逻辑
	public static void decompress() {
		try {
			UnzipDemo.decompress("d:\\tomcat.zip");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
