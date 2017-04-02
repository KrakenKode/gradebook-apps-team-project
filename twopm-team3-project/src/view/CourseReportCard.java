package view;

import java.text.AttributedString;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.util.Rotation;

import model.*;


public class CourseReportCard extends ApplicationFrame{

	public CourseReportCard(Course course) {
		super(course.getName()+" Report Card");
		final PieDataset dataset = createSampleDataset(course.getCategories());
		
		final JFreeChart chart = createChart(dataset);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);
	}

	private PieDataset createSampleDataset(ArrayList<Category> category) {

		final DefaultPieDataset result = new DefaultPieDataset();
		
		for(Category cat: category){
			result.setValue(cat.getName(), cat.catRun());
		}
		return result;

	}

	private JFreeChart createChart(final PieDataset dataset) {

		final JFreeChart chart = ChartFactory.createPieChart3D(
				"Pie Chart 3D Demo 4",  // chart title
				dataset,                // data
				true,                   // include legend
				true,
				false
				);

		final PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		plot.setNoDataMessage("No data to display");
		//plot.setLabelGenerator(new CustomLabelGenerator());
		return chart;

	}
//	 /**
//     * A custom label generator (returns null for one item as a test).
//     */
//    static class CustomLabelGenerator implements PieSectionLabelGenerator {
//        
//        /**
//         * Generates a label for a pie section.
//         * 
//         * @param dataset  the dataset (<code>null</code> not permitted).
//         * @param key  the section key (<code>null</code> not permitted).
//         * 
//         * @return the label (possibly <code>null</code>).
//         */
//        public String generateSectionLabel(final PieDataset dataset, final Comparable key) {
//            String result = null;    
//            if (dataset != null) {
//                if (!key.equals("PHP")) {
//                    result = key.toString();   
//                }
//            }
//            return result;
//        }
//
//		@Override
//		public AttributedString generateAttributedSectionLabel(PieDataset arg0, Comparable arg1) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//   
//    }

}
