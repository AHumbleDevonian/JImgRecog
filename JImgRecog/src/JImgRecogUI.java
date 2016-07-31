import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import javax.swing.Action;

public class JImgRecogUI {

	private JFrame frame;
	private JFrame dialog;
	private JTextField textField;
	private final Action action = new openAction();
	private File inputFile;
	private JLabel panel = new JLabel();
	private JLabel lblError = new JLabel();	
	private final Action action1 = new addBlueAction();
	private JButton btnAddBlue = new JButton("Add Blue");
	private BufferedImage originalImage;
	private BufferedImage image;
	private int startx = 100;
	private int starty = 0;
	private int heightT = 10;
	private JTextField xField;
	private JTextField yField;
	private JTextField heightField;
	private final Action action_1 = new saveAction();
	private JButton btnSave = new JButton("Save");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JImgRecogUI window = new JImgRecogUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JImgRecogUI() {
		initialize();
	}

	private void initialize() {
		JImgRecog test = new JImgRecog();
		test.XORTest();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 412, 239);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		textField = new JTextField();
		textField.setToolTipText("File Name");
		textField.setEditable(false);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("FileName: ");		
		JButton btnOpenFile = new JButton("Open File");
		btnOpenFile.setAction(action);
		btnAddBlue.setAction(action1);		
		lblError.setEnabled(false);		
		btnSave.setAction(action_1);		
		btnSave.setEnabled(false);
		btnAddBlue.setEnabled(false);		
		xField = new JTextField();
		xField.setColumns(10);		
		yField = new JTextField();
		yField.setColumns(10);		
		heightField = new JTextField();
		heightField.setColumns(10);		
		JLabel lblStartX = new JLabel("Start X");		
		JLabel lblStartY = new JLabel("Start Y");		
		JLabel lblHeight = new JLabel("Height");	
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnOpenFile)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddBlue))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStartX)
								.addComponent(lblStartY)
								.addComponent(lblHeight))
							.addGap(13)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(xField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(yField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(heightField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblError, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblError, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnOpenFile)
								.addComponent(btnAddBlue))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(xField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStartX))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(yField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStartY))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(heightField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHeight))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSave)))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		panel.setBackground(Color.WHITE);
		frame.getContentPane().setLayout(groupLayout);
	}
	private class openAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public openAction() {
			putValue(NAME, "Open File");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
				fileChooser.setFileFilter(filter);
				int result = fileChooser.showOpenDialog(dialog);
				if (result == JFileChooser.APPROVE_OPTION) {
					inputFile = fileChooser.getSelectedFile();
					textField.setText(inputFile.getName());
					image = ImageIO.read(inputFile);
					originalImage = cloneImage(image);
				    int width = image.getWidth();
				    int height = image.getHeight();				      
				    if(width <= 150 && height <= 150){
				    	lblError.setEnabled(false);
				    	lblError.setText("");
				    	btnAddBlue.setEnabled(true);
				    	panel.setIcon(new ImageIcon(image));
				    	Color[][] result2 = new Color[height][width];

				    	for (int row = 0; row < height; row++) {
				    		for (int col = 0; col < width; col++) {
				    			result2[row][col] = new Color(image.getRGB(col, row));		            
				    		}
				    	}	
				    	xField.setText(String.valueOf(startx));
				    	yField.setText(String.valueOf(starty));
				    	heightField.setText(String.valueOf(heightT));
				    }
				    else{
				    	lblError.setEnabled(true);
				    	lblError.setText("File is too large! Must be 150 x 150.");
				    }
				}else if (result == JFileChooser.CANCEL_OPTION) {
				}
			} catch (Exception f) {
				f.printStackTrace();
			}
		}
	}
	private class addBlueAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public addBlueAction() {
			putValue(NAME, "Add Blue");
		}
		public void actionPerformed(ActionEvent e) {	
			if(validFields() && validValues()){
			image = cloneImage(originalImage);			
			starty = Integer.valueOf(yField.getText());
			startx = Integer.valueOf(xField.getText());
			heightT = Integer.valueOf(heightField.getText());
			
			for(int i = starty; i < (heightT + starty); i++){
				int y = (i + 1);
				int pixels = 0;
				if(y % 2 == 0){
					pixels = ((y - starty) - 1);
				}
				else{
					pixels = (y - starty);
				}
				for(int j = 0; j < pixels; j++){
					int x = 0; 
					if(j == 0){
						x = startx;
					}
					else if(j % 2 == 0)
					{
						x = (startx - (j / 2));
					}
					else{
						x = (startx + ((j + 1) / 2));
					}
					if(x >= 0 && y >= 0 && x < 150 && y < 150){
						image.setRGB(x, y, Color.blue.getRGB());
					}
				}
			}
			panel.setIcon(new ImageIcon(image));	
			btnSave.setEnabled(true);
			}
			else{
				
			}
		}
	}
	private boolean validValues() {		
		if(startx > 150 || starty > 150){
			xField.setText(String.valueOf(startx));
			yField.setText(String.valueOf(starty));	
			heightField.setText(String.valueOf(heightT));
			return false;
		}
		else if((heightT  + starty) > 150){
			xField.setText(String.valueOf(startx));
			yField.setText(String.valueOf(starty));	
			heightField.setText(String.valueOf(heightT));
			return false;
		}
		return true;		
	}
	
	private boolean validFields(){
		if(isRegexValid(xField.getText(),"^(150)|([0-1][0-4][0-9])|([0-9][0-9])|([0-9])$")
		&&
		isRegexValid(yField.getText(),"^(150)|([0-1][0-4][0-9])|([0-9][0-9])|([0-9])$")
		&& 
		isRegexValid(heightField.getText(),"^(150)|([0-1][0-4][0-9])|([0-9][0-9])|([0-9])$")){
			return true;
		}
		else{
			xField.setText(String.valueOf(startx));
			yField.setText(String.valueOf(starty));	
			heightField.setText(String.valueOf(heightT));
			return false;
		}	
		
	}

	private boolean isRegexValid(String strVal, String pattern){
		if(strVal.matches(pattern)){
			return true;
		}		
		return false;
	}
	
	private static BufferedImage cloneImage(BufferedImage startImage) {
	    ColorModel cm = startImage.getColorModel();
	    boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
	    WritableRaster raster = startImage.copyData(startImage.getRaster().createCompatibleWritableRaster());
	    return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	
	private class saveAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public saveAction() {
			putValue(NAME, "Save");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG (*.jpg)", "jpg");
				fileChooser.addChoosableFileFilter(filter);
				fileChooser.setFileFilter(filter);
				int result = fileChooser.showSaveDialog(dialog);
				
				if (result == JFileChooser.APPROVE_OPTION) {
					String path = fileChooser.getCurrentDirectory().getAbsolutePath();
					String fileName = fileChooser.getSelectedFile().getName();
					File outputfile = new File(path + "/" + fileName + ".jpg");
					ImageIO.write(image, "jpg", outputfile);
				}
			}catch (Exception f) {
			f.printStackTrace();
		}
			
		}
	}
}
