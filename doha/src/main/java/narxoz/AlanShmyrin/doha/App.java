package narxoz.AlanShmyrin.doha;



import java.util.Random;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import narxoz.AlanShmyrin.doha.Templates.AudienceJDBCTemplate;


public class App 
{
    public static void main( String[] args ){
    	@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Config.class);
    	Graphic graph = context.getBean("appGraph", Graphic.class);
    	graph.startApp();
    	
    		
    }
}

