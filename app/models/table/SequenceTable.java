package models.table;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import models.*;

public class SequenceTable extends JTable{
	private static final long serialVersionUID = -2307670873795120374L;
	
	/** 
	 * instance data: jtable, jframe
	 */
	static JTable table;
	static TableModel model;
	
	public SequenceTable(final ArrayList<AminoAcidSequence> alignment){
		/** get number of columns (length of protein) */
		int columnCount = ((AminoAcidSequence) alignment.get(0)).getLength();
		
		/** set up correct model for sequence table */
	    model = new SequenceTableModel(alignment);
	    //table = new JTable(model);
	    //table.setDefaultRenderer(AminoAcidSequence.class, new TableRenderer(alignment));
	    
	    final TableCellRenderer tableRenderer = new TableRenderer(alignment);

	    table = new JTable(model) {

		public TableCellRenderer getCellRenderer(int row, int column) {
	               return tableRenderer;
	        }
	    }; 
	   	table.setDefaultRenderer(Object.class, tableRenderer );

	    
	    /**
	     * add table header renderer 
	     */
	    
	    /** remove column headers */
	    table.setTableHeader(null);
	    
	    /** disable auto-resize so scroll bar will be active */
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    
	    /** set min width of all columns */
	    for(int x = 0; x < columnCount; x++){
	    	table.getColumnModel().getColumn(x).setPreferredWidth(10);
	    }
	    

   

	}
	
	public JTable getTable(){
		return table;
	}
}
