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

public class PageTwo extends WizardPage{
	
	protected PageTwo() {
		super(InstallWizard.P2, "步骤二：部署war包", ImageDescriptor.createFromFile(PageOne.class, "q.gif"));
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
				if ((e1.getCurrentPage() instanceof PageOne) && (e1.getTargetPage() instanceof PageTwo)) {
					moveFile();
					setMessage("部署成功！");
				} else {
					setMessage("部署失败！");
				}
			}
		});
		setControl(composite);
	}
	
	// 部署逻辑
	public static void moveFile() {
		try {
			FileUtil fileUtil = new FileUtil();
			fileUtil.moveFile("d:\\demo-test.war", "d:\\tomcat\\webapps\\");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
