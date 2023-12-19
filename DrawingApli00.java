/**
 * Simple Drawing Application
 * 簡単なお絵かきソフト
 * ・フリーハンド，直線，四角，楕円の描画機能
 * ・四角と楕円は左下方向のみ
 * ・色などの変更機能は無し
 *
 */
import java.awt.*;
import java.awt.event.*;

public class DrawingApli00 extends Frame implements ActionListener, AdjustmentListener, ItemListener {
  // ■ フィールド変数
  Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9; // フレームに配置するボタンの宣言
  Label lb1, lb2, lb3;
  Scrollbar sbar1, sbar2, sbar3;
  Checkbox cbx1, cbx2, cbx3, cbx4;
  String s1 = "";
  int red = 0, green = 0, blue = 0;
  Panel  p_east, p_south, p_checkbox, p_scrollbar;                // ボタン配置用パネルの宣言
  MyCanvas mc;               // 別途作成した MyCanvas クラス型の変数の宣言

  // ■ main メソッド（スタート地点）
  public static void main(String [] args) {
    DrawingApli00 da = new DrawingApli00(); 
  }

  // ■ コンストラクタ
  DrawingApli00() {
    super("Drawing Appli");
    //this.setSize(300, 200);
    this.setSize(1200, 800); 

    p_east = new Panel();       // Panel のオブジェクト（実体）を作成
    p_south = new Panel();
    p_checkbox = new Panel();
    p_scrollbar = new Panel();
    mc = new MyCanvas(this); // mc のオブジェクト（実体）を作成

    this.setLayout(new BorderLayout(10, 10)); // レイアウト方法の指定
    this.add(p_east, BorderLayout.EAST);         // 右側に パネルを配置
    this.add(p_south, BorderLayout.SOUTH);    // 下側に パネルを配置
    this.add(mc,  BorderLayout.CENTER);       // 左側に mc （キャンバス）を配置
                                         // BorerLayout の場合，West と East の幅は
                                         // 部品の大きさで決まる，Center は West と East の残り幅
    p_east.setLayout(new GridLayout(9,1));  // ボタンを配置するため，９行１列のグリッドをパネル上にさらに作成
    bt1 = new Button("Free Hand"); bt1.addActionListener(this); p_east.add(bt1);// ボタンを順に配置
    bt2 = new Button("Line");      bt2.addActionListener(this); p_east.add(bt2);
    bt3 = new Button("Rectangle"); bt3.addActionListener(this); p_east.add(bt3);
    bt4 = new Button("fillRectangle"); bt4.addActionListener(this); p_east.add(bt4);
    bt5 = new Button("Oval");      bt5.addActionListener(this); p_east.add(bt5);
    bt6 = new Button("fillOval");  bt6.addActionListener(this); p_east.add(bt6);
    bt7 = new Button("Erase");     bt7.addActionListener(this); p_east.add(bt7);
    bt8 = new Button("Clear");     bt8.addActionListener(this); p_east.add(bt8);
    //bt9 = new Button("Undo");     bt9.addActionListener(this); p_east.add(bt9);

    //チェックボックスと色の選択(スクロールバー)
    p_south.setLayout(new GridLayout(2,1));

    p_checkbox.setLayout(new GridLayout(1, 4));
    cbx1 = new Checkbox("Brush"); cbx1.addItemListener(this); p_checkbox.add(cbx1);
    cbx2 = new Checkbox("Dotted"); cbx2.addItemListener(this); p_checkbox.add(cbx2);
    cbx3 = new Checkbox("A little thick"); cbx3.addItemListener(this); p_checkbox.add(cbx3);
    cbx4 = new Checkbox("Thick"); cbx4.addItemListener(this); p_checkbox.add(cbx4);
    p_south.add(p_checkbox);

    p_scrollbar.setLayout(new GridLayout(6, 1));
    lb1 = new Label("Red", Label.CENTER); p_scrollbar.add(lb1); // ラベルを配置
    sbar1 = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256); sbar1.addAdjustmentListener(this); p_scrollbar.add(sbar1); // スクロールバーを配置
    lb2 = new Label("Green", Label.CENTER); p_scrollbar.add(lb2); // ラベルを配置
    sbar2 = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256); sbar2.addAdjustmentListener(this); p_scrollbar.add(sbar2); // スクロールバーを配置
    lb3 = new Label("Blue", Label.CENTER); p_scrollbar.add(lb3); // ラベルを配置
    sbar3 = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256); sbar3.addAdjustmentListener(this); p_scrollbar.add(sbar3); // スクロールバーを配置    
    p_south.add(p_scrollbar);

    this.setVisible(true); //可視化
  }

  // ■ メソッド
  // ActionListener を実装しているため、例え内容が空でも必ず記述しなければならない
  public void actionPerformed(ActionEvent e){ // フレーム上で生じたイベントを e で取得
    if (e.getSource() == bt1)      // もしイベントが bt1 で生じたなら
      mc.mode=1;                   // モードを１に
    else if (e.getSource() == bt2) // もしイベントが bt2 で生じたなら
      mc.mode=2;                   // モードを２に
    else if (e.getSource() == bt3) // もしイベントが bt3 で生じたなら
      mc.mode=3;                   // モードを３に
    else if (e.getSource() == bt4) // もしイベントが bt4 で生じたなら
      mc.mode=4;                   // モードを４に
    else if (e.getSource() == bt5) // もしイベントが bt5 で生じたなら
      mc.mode=5;                   // モードを５に
    else if (e.getSource() == bt6) // もしイベントが bt6 で生じたなら
      mc.mode=6;                   // モードを６に
    else if (e.getSource() == bt7) // もしイベントが bt7 で生じたなら
      mc.mode=7;                   // モードを７に
    else if (e.getSource() == bt8) // もしイベントが bt8 で生じたなら
      mc.mode=8;                   // モードを８に
      //mc.clearCanvas();
    else if (e.getSource() == bt9) // もしイベントが bt9 で生じたなら
      mc.mode=9;                   // モードを９に
  }

  // AdjustmentListene を実装しているため、例え内容が空でも必ず記述しなければならない
  // スクロールバーのいずれかが動かされたら呼び出される
  public void adjustmentValueChanged(AdjustmentEvent e) {
    Scrollbar sbar = (Scrollbar)e.getAdjustable();
    if (sbar == sbar1 || sbar == sbar2 ||sbar == sbar3) {
      red   = sbar1.getValue();
      green = sbar2.getValue();
      blue  = sbar3.getValue();
      s1 = "Red:" + red + " Green:" + green + " Blue:" + blue;
      mc.setColorValue(red, green, blue, s1);
    }
    //repaint();
  }

  // チェックボックスが「操作されると」 itemStateChanged が呼び出される
  public void itemStateChanged(ItemEvent e) {
    Checkbox checkbox = (Checkbox)e.getItemSelectable();
    if (checkbox == cbx1) {
      mc.setBrush(checkbox.getState());
    } else if (checkbox == cbx2) {
      mc.setDotted(checkbox.getState());
    } else if (checkbox == cbx3) {
      mc.setThick1(checkbox.getState());
      mc.setThick2(false); // 太さのオプションが1つだけ選択されていることを確認
      cbx4.setState(false); // チェックを外す
    } else if (checkbox == cbx4) {
      mc.setThick2(checkbox.getState());
      mc.setThick1(false); // 太さのオプションが1つだけ選択されていることを確認
      cbx3.setState(false);
    }
    //repaint();
  }
}

/**
 * Extended Canvas class for DrawingApli
 * [各モードにおける処理内容]
 * 1: free hand 
 *      pressed -> set x, y,  dragged  -> drawline & call repaint()
 * 2: draw line 
 *      pressed -> set x, y,  released -> drawline & call repaint()
 * 3: rect
 *      pressed -> set x, y,  released -> calc w, h & call repaint()
 * 4: circle
 *      pressed -> set x, y,  released -> calc w, h & call repaint()
 * 
 */
class MyCanvas extends Canvas implements MouseListener, MouseMotionListener {
  // ■ フィールド変数
  int x, y;   // マウスポインタの現在の位置
  int px, py; // 前回の位置
  int ow, oh; // width and height of the object
  int mode;   // drawing mode associated as below
  String s1 = "";
  int red = 0, green = 0, blue = 0;
  boolean check_brush = false, check_dotted = false, check_thick1 = false, check_thick2 = false;
  Image img = null;   // 仮の画用紙
  Graphics gc = null; // 仮の画用紙用のペン
  Dimension d; // キャンバスの大きさ取得用

  // ■ コンストラクタ
  MyCanvas(DrawingApli00 obj){
    mode=0;                       // initial value 
    this.setSize(500,400);        // キャンバスのサイズを指定
    addMouseListener(this);       // マウスのボタンクリックなどを監視するよう指定
    addMouseMotionListener(this); // マウスの動きを監視するよう指定
  }

  // ■ メソッド（オーバーライド）
  // フレームに何らかの更新が行われた時の処理
  public void update(Graphics g) {
    paint(g); // 下記の paint を呼び出す
  }

  // ■ メソッド
  // スクロールバーから得た色の値を受け取る
  public void setColorValue(int red, int green, int blue, String s1) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.s1 = s1;
  }

  // ■ メソッド
  // チェックが押されたかを判定
  public void setBrush(boolean isValid) {
    check_brush = isValid;
  }
  public void setDotted(boolean isValid) {
    check_dotted = isValid;
  }
  public void setThick1(boolean isValid) {
    check_thick1 = isValid;
  }
  public void setThick2(boolean isValid) {
    check_thick2 = isValid;
  }

  // ■ メソッド（オーバーライド）
  public void paint(Graphics g) {
    d = getSize();   // キャンバスのサイズを取得
    if (img == null) // もし仮の画用紙の実体がまだ存在しなければ
      img = createImage(d.width, d.height); // 作成
    if (gc == null)  // もし仮の画用紙用のペン (GC) がまだ存在しなければ
      gc = img.getGraphics(); // 作成

    if (check_brush == true) {
      gc.setColor(new Color(red, green, blue, 50)); // ブラシ機能
    } else {
      gc.setColor(new Color(red, green, blue)); // 通常のペンの色
    }

    // ペンの太さと破線
    Graphics2D gc2 = (Graphics2D)gc;
    if (check_dotted == true) {
      if (check_thick1 == true) {
        gc2.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, new float[] {6, 25}, 0));
      } else if (check_thick2 == true) {
        gc2.setStroke(new BasicStroke(20, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, new float[] {6, 70}, 0));
      } else {
        gc2.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, new float[] {6}, 0));
      }
    } else if (check_thick1 == true) {
      gc2.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    } else if (check_thick2 == true) {
      gc2.setStroke(new BasicStroke(20, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    } else {
      gc2.setStroke(new BasicStroke(1));
    }

    switch (mode){
    case 1: // モードが１の場合
      gc.drawLine(px, py, x, y); // 仮の画用紙に描画
      break;
    case 2: // モードが２の場合
      gc.drawLine(px, py, x, y); // 仮の画用紙に描画
      break;
    case 3: // モードが３の場合
      if (x > px && y > py) gc.drawRect(px, py, ow, oh); // 仮の画用紙に描画
      if (x > px && y < py) gc.drawRect(px, y, ow, oh);
      if (x < px && y > py) gc.drawRect(x, py, ow, oh);
      if (x < px && y < py) gc.drawRect(x, y, ow, oh);
      break;
    case 4: // モードが４の場合
      if (x > px && y > py) gc.fillRect(px, py, ow, oh); // 仮の画用紙に描画
      if (x > px && y < py) gc.fillRect(px, y, ow, oh);
      if (x < px && y > py) gc.fillRect(x, py, ow, oh);
      if (x < px && y < py) gc.fillRect(x, y, ow, oh);
      break;
    case 5: // モードが５の場合
      if (x > px && y > py) gc.drawOval(px, py, ow, oh); // 仮の画用紙に描画
      if (x > px && y < py) gc.drawOval(px, y, ow, oh);
      if (x < px && y > py) gc.drawOval(x, py, ow, oh);
      if (x < px && y < py) gc.drawOval(x, y, ow, oh);
      break;
    case 6: // モードが６の場合
      if (x > px && y > py) gc.fillOval(px, py, ow, oh); // 仮の画用紙に描画
      if (x > px && y < py) gc.fillOval(px, y, ow, oh);
      if (x < px && y > py) gc.fillOval(x, py, ow, oh);
      if (x < px && y < py) gc.fillOval(x, y, ow, oh);
      break;
    case 7: // モードが７の場合
      gc.setColor(this.getBackground());
      gc.drawLine(px, py, x, y); // 仮の画用紙に描画
      break;
    case 8: // モードが８の場合
      gc.clearRect(0, 0, d.width, d.height); // キャンバスをクリア
      break;
    }

    //g.setColor(Color.BLACK);       // ペンの色設定
    //g.drawString(s1, 10, getSize().height - 1); // 文字列表示1

    g.drawImage(img, 0, 0, this); // 仮の画用紙の内容を MyCanvas に描画
  }

  // ■ メソッド
  // 下記のマウス関連のメソッドは，MouseListener をインターフェースとして実装しているため
  // 例え使わなくても必ず実装しなければならない
  public void mouseClicked(MouseEvent e){}// 今回は使わないが、無いとコンパイルエラー
  public void mouseEntered(MouseEvent e){}// 今回は使わないが、無いとコンパイルエラー
  public void mouseExited(MouseEvent e){} // 今回は使わないが、無いとコンパイルエラー
  public void mousePressed(MouseEvent e){ // マウスボタンが押された時の処理
    switch (mode){
    case 1: // mode が１もしくは
    case 7: // ７の場合，次の内容を実行する
      x = e.getX();
      y = e.getY();
      break;
    case 2: // mode が２もしくは
    case 3: // ３もしくは
    case 4: // ４もしくは
    case 5: // ５もしくは
    case 6: // ６の場合，次の内容を実行する
      px = e.getX();
      py = e.getY();
      break;
    case 8:
      repaint(); // 再描画
    }
  }
  public void mouseReleased(MouseEvent e){ // マウスボタンが離された時の処理
    switch (mode){
    case 2: // mode が２もしくは
    case 3: // ３もしくは
    case 4: // ４もしくは
    case 5: // ５もしくは
    case 6: // ６の場合，次の内容を実行する
      x = e.getX();
      y = e.getY();
      ow = Math.abs(x-px);
      oh = Math.abs(y-py);
      repaint(); // 再描画
    }
  }
  
  // ■ メソッド
  // 下記のマウス関連のメソッドは，MouseMotionListener をインターフェースとして実装しているため
  // 例え使わなくても必ず実装しなければならない
  public void mouseDragged(MouseEvent e){ // マウスがドラッグされた時の処理
    switch (mode){
    case 1: // mode が１もしくは
    case 7: // ７の場合，次の内容を実行する
      px = x;
      py = y;
      x = e.getX();
      y = e.getY();
      repaint(); // 再描画
      break;
    /*case 2:
      x = e.getX();
      y = e.getY();
      repaint(); // 再描画
      break;
    case 3:
    case 4:
    case 5:
    case 6:
      x = e.getX();
      y = e.getY();
      ow = Math.abs(x - px);
      oh = Math.abs(y - py);
      repaint(); // 再描画
      break;*/
    }
  }
  public void mouseMoved(MouseEvent e){} // 今回は使わないが、無いとコンパイルエラー

  /*public void clearCanvas() {
    gc.clearRect(0, 0, d.width, d.height);
    repaint();
  }*/
}

//クリックしたらクリア
//undo 機能の実装
//文字入力機能の実装