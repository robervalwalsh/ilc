import hep.aida.*;
import java.util.Random;
import java.lang.Boolean;
import org.freehep.application.*;
import org.freehep.application.studio.*;

public class ftplots_3 {
  
  public static void main(String[] argc) throws java.io.IOException {
    
    int nn = 3;
    int ntags = 3; 
    
    String aidaFile[] = new String[nn];
    
    String [] dirName = {"./"};
    
    aidaFile[0] =  "lcfiaidaplots_LDCPrime_02Sc_ldcprime_02sc_JP+NN.aida";
    aidaFile[1] =  "lcfiaidaplots_ILD_00_ldcprime_02sc_JP+NN.aida";
    aidaFile[2] =  "lcfiaidaplots_ILD_00_gldprim_v04_JP+NN.aida";
      
    IAnalysisFactory af = IAnalysisFactory.create();
    
    ITree tree[];
    IDataPointSet btag[];
    IDataPointSet ctag[];
    IDataPointSet bctag[];
    
    tree  = new ITree[nn];
    btag  = new IDataPointSet[nn];
    ctag  = new IDataPointSet[nn];
    bctag = new IDataPointSet[nn];
    
    for ( int i = 0; i < nn; i++ ) {
      tree[i] = af.createTreeFactory().create(dirName[0]+aidaFile[i]);
    }
    
    String [] plot_all = {
      "/MyLCFIAIDAPlotProcessor/FlavourTag_2Jets/AnyNumberOfVertices/Purity-Efficiency for B-Tag  (AnyNumberOfVertices)",
      "/MyLCFIAIDAPlotProcessor/FlavourTag_2Jets/AnyNumberOfVertices/Purity-Efficiency for C-Tag  (AnyNumberOfVertices)",
      "/MyLCFIAIDAPlotProcessor/FlavourTag_2Jets/AnyNumberOfVertices/Purity-Efficiency for BC-Tag  (AnyNumberOfVertices)",
    };
    
    String [] color = {
      "red",
      "blue",
      "green",
    };
      
    String [] shape = { "0", "8", "4" };
    
//    IPlotter plotter[] = new IPlotter[1];
    
    IPlotter plotAll = af.createPlotterFactory().create("efficiency x purity (any number of vertices)");
    plotAll.createRegions(1);
    plotAll.setParameter("plotterWidth","1200");
    plotAll.setParameter("plotterHeight","800");
    
    plotAll.currentRegion().setXLimits(-0.04,1.04);
    plotAll.currentRegion().setYLimits(-0.04,1.04);
    
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
    
    style.dataStyle().markerStyle().setParameter("size","10");
    style.dataStyle().outlineStyle().setParameter("type","0");
    
    // 1 vtx
    for ( int i = 0; i < nn; i++) {
       btag[i]  = (IDataPointSet) tree[i].find(plot_all[0]);
       ctag[i]  = (IDataPointSet) tree[i].find(plot_all[1]);
       bctag[i] = (IDataPointSet) tree[i].find(plot_all[2]);
       style.dataStyle().markerStyle().setParameter("shape",shape[i]);
       
       style.dataStyle().markerStyle().setParameter("color",color[0]);
       style.dataStyle().outlineStyle().setParameter("color",color[0]);
       style.dataStyle().errorBarStyle().setParameter("color",color[0]);
       plotAll.region(0).plot(btag[i],style);
       style.dataStyle().markerStyle().setParameter("color",color[1]);
       style.dataStyle().outlineStyle().setParameter("color",color[1]);
       style.dataStyle().errorBarStyle().setParameter("color",color[1]);
       plotAll.region(0).plot(bctag[i],style);
       style.dataStyle().markerStyle().setParameter("color",color[2]);
       style.dataStyle().outlineStyle().setParameter("color",color[2]);
       style.dataStyle().errorBarStyle().setParameter("color",color[2]);
       plotAll.region(0).plot(ctag[i],style);
   }    
    
    plotAll.show();
    
    plotAll.writeToFile("ftplots_ldcXild.eps","EPS");
  }
}    
