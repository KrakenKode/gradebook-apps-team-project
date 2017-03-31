package model;

public class ModelTest {

	public static void main(String[] args) {
		GradeBookModel model = new GradeBookModel();
		
		
		for(Semester semester : model.getSemesters()) {
			System.out.println(semester.getName());
			
			for(Course course : semester.getCourses()) {
				System.out.println("\t" + course.getName());
				
				for(Category category : course.getCategories()) {
					System.out.println("\t\t" + category.getName());
					
					for(Grade grade : category.getGrades()) {
						System.out.printf("\t\t\t%s %d %d\n", grade.getName(), grade.getPoints(), grade.getMaxPoints());
						
					}
					
				}
				
			}
			
		} // end giant loops

		model.saveFile();

	}

}
