import hep.aida.*;
import java.util.Random;
import java.lang.Boolean;
import org.freehep.application.*;
import org.freehep.application.studio.*;

public class pureff_nets {
  
  public static void main(String[] argc) throws java.io.IOException {
    
    int nnets = 4;
    int ntags = 3; 
    
    String aidaFile[] = new String[nnets];
    
    String [] dirName = {"/Home/rwrangel/physics/ilc/analysis/LCFI_tuning/purity_efficiency/plots"};
    for ( int i = 0; i < nnets; i++) {
      aidaFile[i] = "/LCFIPlots-new_JPP_DU+new_NN_DU_nnets" + (i+1) + ".aida";
    }
      
    IAnalysisFactory af = IAnalysisFactory.create();
    
    ITree tree[];
    IDataPointSet btag[];
    IDataPointSet ctag[];
    IDataPointSet bctag[];
    
    tree  = new ITree[nnets];
    btag  = new IDataPointSet[nnets];
    ctag  = new IDataPointSet[nnets];
    bctag = new IDataPointSet[nnets];
    
    for ( int i = 0; i < nnets; i++ ) {
      tree[i] = af.createTreeFactory().create(dirName[0]+aidaFile[i]);
    }
    
    String [] plot_all = {
      "/MyLCFIAIDAPlotProcessor/FlavourTag_2Jets/AnyNumberOfVertices/Purity-Efficiency for B-Tag  (AnyNumberOfVertices)",
      "/MyLCFIAIDAPlotProcessor/FlavourTag_2Jets/AnyNumberOfVertices/Purity-Efficiency for C-Tag  (AnyNumberOfVertices)",
      "/MyLCFIAIDAPlotProcessor/FlavourTag_2Jets/AnyNumberOfVertices/Purity-Efficiency for BC-Tag  (AnyNumberOfVertices)",
    };
    
    String [] plot_1vtx = {
      "/MyLCFIAIDAPlotProcessor/FlavourTag_2Jets/OneVertex/Purity-Efficiency for B-Tag  (OneVertex)",
      "/MyLCFIAIDAPlotProcessor/FlavourTag_2Jets/OneVertex/Purity-Efficiency for C-Tag  (OneVertex)",
      "/MyLCFIAIDAPlotProcessor/FlavourTag_2Jets/OneVertex/Purity-Efficiency for BC-Tag  (OneVertex)",
    };
    
    String [] plot_2vtx = {
      "/MyLCFIAIDAPlotProcessor/FlavourTag_2Jets/TwoVertices/Purity-Efficiency for B-Tag  (TwoVertices)",
      "/MyLCFIAIDAPlotProcessor/FlavourTag_2Jets/TwoVertices/Purity-Efficiency for C-Tag  (TwoVertices)",
      "/MyLCFIAIDAPlotProcessor/FlavourTag_2Jets/TwoVertices/Purity-Efficiency for BC-Tag  (TwoVertices)",
    };
    
    String [] plot_3vtx = {
      "/MyLCFIAIDAPlotProcessor/FlavourTag_2Jets/ThreeOrMoreVertices/Purity-Efficiency for B-Tag  (ThreeOrMoreVertices)",
      "/MyLCFIAIDAPlotProcessor/FlavourTag_2Jets/ThreeOrMoreVertices/Purity-Efficiency for C-Tag  (ThreeOrMoreVertices)",
      "/MyLCFIAIDAPlotProcessor/FlavourTag_2Jets/ThreeOrMoreVertices/Purity-Efficiency for BC-Tag  (ThreeOrMoreVertices)",
    };
      
    String [] color = {
      "black",
      "green",
      "blue",
      "red"
    };
    
//    IPlotter plotter[] = new IPlotter[1];
    
    IPlotter plotAll = af.createPlotterFactory().create("efficiency x purity");
    plotAll.createRegions(1);
    plotAll.setParameter("plotterWidth","1200");
    plotAll.setParameter("plotterHeight","800");
    
    IPlotterStyle [] prs = new IPlotterStyle[1];
    IPlotterStyle style = af.createPlotterFactory().createPlotterStyle();
    style.legendBoxStyle().setVisible(false);
    style.titleStyle().setVisible(false);
    
    style.xAxisStyle().setParameter("lowerLimit","0.5");
    
    style.xAxisStyle().setLabel("Efficiency");
    style.xAxisStyle().labelStyle().setFontSize(32);
    style.xAxisStyle().labelStyle().setBold(true);
    style.xAxisStyle().labelStyle().setItalic(true);
    style.xAxisStyle().tickLabelStyle().setFontSize(20);
    style.xAxisStyle().tickLabelStyle().setBold(true);
   
    style.yAxisStyle().setLabel("Purity");
    style.yAxisStyle().labelStyle().setFontSize(32);
    style.yAxisStyle().labelStyle().setBold(true);
    style.yAxisStyle().labelStyle().setItalic(true);
    style.yAxisStyle().tickLabelStyle().setFontSize(20);
    style.yAxisStyle().tickLabelStyle().setBold(true);
    
    style.dataStyle().markerStyle().setParameter("size","8");
    style.dataStyle().outlineStyle().setParameter("type","0");
    style.dataStyle().markerStyle().setParameter("shape","8");
    
    // 1 vtx
    for ( int i = 0; i < nnets; i++) {
       style.dataStyle().markerStyle().setParameter("color",color[i]);
       style.dataStyle().errorBarStyle().setParameter("color",color[i]);
       style.dataStyle().outlineStyle().setParameter("color",color[i]);
       btag[i]  = (IDataPointSet) tree[i].find(plot_1vtx[0]);
       ctag[i]  = (IDataPointSet) tree[i].find(plot_1vtx[1]);
       bctag[i] = (IDataPointSet) tree[i].find(plot_1vtx[2]);
       plotAll.region(0).plot(bctag[i],style);
    }    
    
    plotAll.show();
    
    plotAll.writeToFile("nnets_bctag_1vtx.eps","EPS");
  }
}    
