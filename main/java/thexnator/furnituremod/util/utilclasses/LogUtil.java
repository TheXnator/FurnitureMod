package thexnator.furnituremod.util.utilclasses;

import static org.apache.logging.log4j.Level.ALL;
import static org.apache.logging.log4j.Level.DEBUG;
import static org.apache.logging.log4j.Level.ERROR;
import static org.apache.logging.log4j.Level.FATAL;
import static org.apache.logging.log4j.Level.INFO;
import static org.apache.logging.log4j.Level.OFF;
import static org.apache.logging.log4j.Level.TRACE;
import static org.apache.logging.log4j.Level.WARN;

import org.apache.logging.log4j.Level;

import net.minecraftforge.fml.common.FMLLog;
import thexnator.furnituremod.Reference;

public class LogUtil{
	
	public static void all(Object object){
		log(ALL, object);
	}
	
	public static void debug(Object object){
		log(DEBUG, object);
	}
	
	public static void error(Object object){
		log(ERROR, object);
	}
	
	public static void fatal(Object object){
		log(FATAL, object);
	}
	
	public static void info(Object object){
		log(INFO, object);
	}
	
	public static void off(Object object){
		log(OFF, object);
	}
	
	public static void trace(Object object){
		log(TRACE, object);
	}
	
	public static void warn(Object object){
		log(WARN, object);
	}
	
	private static void log(Level logLevel, Object object){
		String[] lines=object.toString().split("\n");
		for(String line:lines)FMLLog.log(Reference.MOD_NAME, logLevel, line);
	}
}