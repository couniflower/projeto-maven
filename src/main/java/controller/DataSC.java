package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public abstract class DataSC {
    protected final DateTimeFormatter data = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    protected final DateTimeFormatter dataSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected final DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm");
    private final SimpleDateFormat dateData = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat dateDataSQL = new SimpleDateFormat("yyyy-MM-dd");

    public Date toDate(Object object) {
        Date d = null;
        if(object instanceof LocalDateTime) {
            d = Date.from(((LocalDateTime) object).atZone(ZoneId.systemDefault()).toInstant());
        }
        if(object instanceof String) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            try {
                d = formato.parse((String) object);
            } catch (ParseException e) {
                e.printStackTrace(); // não sei pra que ?
            }
        }
        return d;
    }

    public LocalDateTime toLocalDateTime(Object object) {
        LocalDateTime localDateTime = null;
        if(object instanceof Date) {
            localDateTime = ((Date) object).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
        if(object instanceof String) {
            localDateTime = LocalDateTime.parse((String) object, data);
        }
        return localDateTime;
    }

    public String toStringSql(Object object) {
        String dataFormatada = null;
        if(object instanceof Date) {
            dataFormatada = dateDataSQL.format((Date) object);
        }
        if(object instanceof LocalDateTime) {
            dataFormatada = dataSQL.format((LocalDateTime) object);
        }
        return dataFormatada;
    }

    public String toStringJava(Object object) {
        String dataFormatada = null;
        if(object instanceof Date) {
            dataFormatada = dateData.format((Date) object);
        }
        if(object instanceof LocalDateTime) {
            dataFormatada = data.format((LocalDateTime) object);
        }
        return dataFormatada;
    }

//    public String toString(Object object) {
//        if(object instanceof LocalDateTime) {
//            return ((LocalDateTime) object).format();
//        }
//        if(object instanceof String) {
//            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//            try {
//                return formato.parse((String) object);
//            } catch (ParseException e) {
//                e.printStackTrace(); // não sei pra que ?
//            }
//        }
//        return null;
//    }

//    public String toSQL(String data) {
//        return data.substring(6, 10) + "-" + data.substring(3, 5) + "-" + data.substring(0, 2);
//    }
//
//    public String toJava(String data) {
//        return data.substring(8, 10) + data.substring(5, 7) + data.substring(0, 4);
//    }
}
