package narxoz.AlanShmyrin.doha;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import narxoz.AlanShmyrin.doha.Mappers.Audience;
import narxoz.AlanShmyrin.doha.Templates.AudienceJDBCTemplate;
import narxoz.AlanShmyrin.doha.Templates.ComputersJDBCTemplate;
import narxoz.AlanShmyrin.doha.Templates.UserJDBCTemplate;


@Component
public class Graphic {
	private static AudienceJDBCTemplate audit;
	private static ComputersJDBCTemplate comp;
	private static UserJDBCTemplate user;
	private static final Frame frame = new Frame("Doha");
	public Graphic(AudienceJDBCTemplate audit, ComputersJDBCTemplate comp, UserJDBCTemplate user) {
		Graphic.audit = audit;
		Graphic.comp = comp;
		Graphic.user = user;
		frame.setLocation(700, 400);
		frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
		
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	private static void main() {
		Button bLogin = new Button("Войти");
		Label lLogin = new Label("Вход");
		Label lName = new Label("Пользователь");
		Label lPass = new Label("Пароль");
		final Label lError = new Label();
		final TextField name = new TextField();
		final TextField pass = new TextField();
		
		lError.setAlignment(Label.CENTER);
		lLogin.setAlignment(Label.CENTER);
		lName.setAlignment(Label.CENTER);
		lPass.setAlignment(Label.CENTER);
		
		lLogin.setBounds(100,50,100,20);
		lName.setBounds(100,120,100,20);
		name.setBounds(100,140,100,20);
		lPass.setBounds(100,190,100,20);
		pass.setBounds(100,210,100,20);
		bLogin.setBounds(100,260,100,20);
		lError.setBounds(100,300,100,20);
		
		bLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (user.login(name.getText(), pass.getText()) == 1) { 
					frame.removeAll();
					Admin.main(frame);
					}
				else if (user.login(name.getText(), pass.getText()) == 0) { 
					frame.removeAll();
					User.main(frame);
					}
				else {lError.setText("Error");}
				
				
			}
			
		});
		
		frame.add(lLogin);
		frame.add(lName);
		frame.add(name);
		frame.add(lPass);
		frame.add(pass);
		frame.add(bLogin);
		frame.add(lError);
		
		frame.setSize(300, 400);
		
	}
	
	public void startApp() {
		main();
	}
	
	static class Admin extends Graphic{

		public Admin(AudienceJDBCTemplate audit, ComputersJDBCTemplate comp) {
			super(audit, comp, user);
			// TODO Auto-generated constructor stub
		}
		
		public static void main(final Frame frame) {
			final List<Label> ls = new ArrayList<>();
			List<Label> dorts = new ArrayList<>();
			frame.setSize(700, 500);
			Button bCancel = new Button("Cancel");
			bCancel.setBounds(550, 450, 100, 20);
			bCancel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					frame.removeAll();
					main();
					
				}});
			for(int j = 0; j < 3; j++) {
				for(int p = 0; p < 8; p++) {
					final int fin = p + j*8;
					ls.add(fin, new Label("Компьютер " + (fin+1)));
					ls.get(fin).setBounds(50 + 200*j, 50 + 50*(p), 200, 20);
					dorts.add(new Label(" "));
					dorts.get(fin).setBounds(150 + 200*j, 55 + 50*(p), 10, 10);
					ls.get(fin).addMouseListener(new MouseListener() {
						public void mouseClicked(MouseEvent e) {
							frame.removeAll();
							computerWindow(fin+1, frame);
						}

						@Override
						public void mousePressed(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}
					});
					if (audit.getById(fin+1).getStatus().equals("работает")) 
						dorts.get(fin).setBackground(new Color(51,255,51));
					else if (audit.getById(fin+1).getStatus().equals("не работает")) 
						dorts.get(fin).setBackground(new Color(255,51,51));
					else dorts.get(fin).setBackground(new Color(255,255,0));
					frame.add(dorts.get(fin));
					frame.add(ls.get(fin));
					frame.add(bCancel);
				}
			}
		}
		
		private static void computerWindow(final int id, final Frame frame) {
			Button bAdd = new Button("Add");
			Button bCancel = new Button("Cancel");
			Label l = new Label();
			Audience data = audit.getById(id);
			
			List<Label> labels = new ArrayList<>();
			final List<TextField> text = new ArrayList<>();
			
			frame.setSize(500,500);
			
			for(int i = 0; i < 6; i++) {
				text.add(i ,new TextField());
				text.get(i).setBounds(300, 50*(i+1)+50, 180, 20);
				labels.add(i, new Label());
				labels.get(i).setBounds(50, 50*(i+1)+50, 180, 20);
			}
			
			
			
			l.setText("Компьютер " + id);
			l.setBounds(50,50, 100, 20);
			
			text.get(0).setText(data.getStatus()); labels.get(0).setText("Status");
			text.get(1).setText(data.getReason()); labels.get(1).setText("Reason");
			text.get(2).setText(data.getVideocard()); labels.get(2).setText("Videocard");
			text.get(3).setText(data.getProcessor()); labels.get(3).setText("Processor");
			text.get(4).setText(data.getRam()); labels.get(4).setText("RAM");
			text.get(5).setText(data.getFreeDiskSpace()); labels.get(5).setText("Free space on disk");
			
			
			bCancel.setBounds(400,450,50,20);
			bAdd.setBounds(300, 450, 50, 20);
			
			bAdd.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					comp.update(id, text.get(2).getText(), text.get(3).getText(), text.get(5).getText(), text.get(4).getText());
					audit.update(id, text.get(0).getText(), text.get(1).getText());
					frame.removeAll();
					main(frame);
				}
				
			});
			
			bCancel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					frame.removeAll();
					main(frame);
				}
				
			});
			
			
			
			frame.add(bAdd);
			frame.add(bCancel);
			frame.add(l);
			
			for(int i = 0; i < 6; i++) {
				frame.add(text.get(i));
				frame.add(labels.get(i));
			}
			
			
			
			
		}
		
		
		
	}
	
	static class User extends Graphic{

		public User(AudienceJDBCTemplate audit, ComputersJDBCTemplate comp) {
			super(audit, comp, user);
			// TODO Auto-generated constructor stub
		}
		
		public static void main(final Frame frame) {
			final List<Label> ls = new ArrayList<>();
			frame.setSize(700, 500);
			Button bCancel = new Button("Cancel");
			bCancel.setBounds(550, 450, 100, 20);
			bCancel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					frame.removeAll();
					main();
					
				}});
			for(int j = 0; j < 3; j++) {
				for(int p = 0; p < 8; p++) {
					final int fin = p + j*8;
					ls.add(fin, new Label("Компьютер " + (fin+1)));
					ls.get(fin).setBounds(50 + 200*j, 50 + 50*(p), 200, 20);
					ls.get(fin).addMouseListener(new MouseListener() {
						public void mouseClicked(MouseEvent e) {
							frame.removeAll();
							computerWindow(fin+1, frame);
						}

						@Override
						public void mousePressed(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}
					});
					frame.add(ls.get(fin));
					frame.add(bCancel);
				}
			}
		}
		
		private static void computerWindow(final int id, final Frame frame) {
			Button bCancel = new Button("Cancel");
			Button bRequest = new Button("Request");
			Label l = new Label();
			
			List<Label> labels = new ArrayList<>();
			final List<TextField> text = new ArrayList<>();
			
			frame.setSize(500,500);
			
			for(int i = 0; i < 6; i++) {
				text.add(i ,new TextField());
				text.get(i).setBounds(300, 50*(i+1)+50, 180, 20);
				text.get(i).disable();
				labels.add(i, new Label());
				labels.get(i).setBounds(50, 50*(i+1)+50, 180, 20);
			}
			
			Audience data = audit.getById(id);
			
			l.setText("Компьютер " + id);
			l.setBounds(50,50, 100, 20);
			
			text.get(0).setText(data.getStatus()); labels.get(0).setText("Status");
			text.get(1).setText(data.getReason()); labels.get(1).setText("Reason");
			text.get(2).setText(data.getVideocard()); labels.get(2).setText("Videocard");
			text.get(3).setText(data.getProcessor()); labels.get(3).setText("Processor");
			text.get(4).setText(data.getRam()); labels.get(4).setText("RAM");
			text.get(5).setText(data.getFreeDiskSpace()); labels.get(5).setText("Free space on disk");
			
			text.get(0).enable();
			text.get(1).enable();
			
			bCancel.setBounds(400,450,70,20);
			
			
			bCancel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					frame.removeAll();
					main(frame);
				}
				
			});
			
			bRequest.setBounds(300, 450, 70, 20);
			
			bRequest.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					comp.update(id, text.get(2).getText(), text.get(3).getText(), text.get(5).getText(), text.get(4).getText());
					audit.update(id, text.get(0).getText(), text.get(1).getText());
					frame.removeAll();
					main(frame);
				}
				
			});
			
			
			frame.add(bRequest);
			frame.add(bCancel);
			frame.add(l);
			
			for(int i = 0; i < 6; i++) {
				frame.add(text.get(i));
				frame.add(labels.get(i));
			}
			
			
			
			
		}
		
		
		
	}
	   
}

