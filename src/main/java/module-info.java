
module fr.isika.CDA25.Projet1_All {
    requires javafx.controls;
    exports fr.isika.CDA25.fx;
    
    opens TestProject1 to javafx.base;

} 