package com.hik.wizard;

import org.eclipse.jface.dialogs.IPageChangingListener;
import org.eclipse.jface.dialogs.PageChangingEvent;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.hik.util.FileUtil;

public class PageThree extends WizardPage{
	
	protected PageThree() {
		super(InstallWizard.P3, "步骤三：启动tomcat", ImageDescriptor.createFromFile(PageOne.class, "q.gif"));
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
				if ((e1.getCurrentPage() instanceof PageTwo) && (e1.getTargetPage() instanceof PageThree)) {
					runCmd();
					setMessage("启动成功！");
				} else {
					setMessage("启动失败！");
				}
			}
		});
		setControl(composite);
	}
	
	// 启动逻辑
	public static void runCmd() {
		try {
			FileUtil fileUtil = new FileUtil();
			fileUtil.runCmd("d:\\tomcat\\bin\\startup.bat");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
