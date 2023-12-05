RESIZE FORM CODING

using System;
using System.Collections.Generic; using System.ComponentModel; using System.Data;
using System.Data.SqlClient ; using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace GraphicalPassword
{
public partial class frmResize : Form
{
public frmResize()
{
InitializeComponent();
}

private void frmResize_Load(object sender, EventArgs e)
{
if (cls.con.State != ConnectionState.Open) cls.con.Open();
cls.cmd.CommandText = "Select ImageId From
 
Images";
 


SqlDataReader r;
r = cls.cmd.ExecuteReader(); while (r.Read())
{
 


42
private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
{
if (cls.con.State != ConnectionState.Open) cls.con.Open();
 
cls.cmd.CommandText = "Select ImageId,ImageData From Images Where ImageId=" + comboBox1.Text ;
SqlDataReader r;
r = cls.cmd.ExecuteReader(); r.Read();
pictureBox1.Tag = r["ImageId"].ToString(); byte [] b = (byte[])(r["ImageData"]); r.Close();
System.IO.MemoryStream ms = new System.IO.MemoryStream(b);
Image img = Image.FromStream(ms); pictureBox1.Image = img; label4.Text = img.Width.ToString(); label5.Text = img.Height.ToString(); pictureBox1.SizeMode =
PictureBoxSizeMode.StretchImage;
}

private void btnSave_Click(object sender, EventArgs
e)
{
Bitmap bmp = new Bitmap(pictureBox1.Image,
int.Parse(textBox1.Text), int.Parse(textBox2.Text));

pictureBox1.Width = int.Parse(textBox1.Text); pictureBox2.Height= int.Parse(textBox2.Text); pictureBox2.Image = bmp;


cls.cmd.CommandText = "Update Images Set ImageData=@ImageData Where imageid=" + comboBox1.Text ;

SqlParameter p1 = new SqlParameter("@ImageData", SqlDbType.Image);

System.IO.MemoryStream ms = new System.IO.MemoryStream();

pictureBox2.Image.Save(ms, System.Drawing.Imaging.ImageFormat.Jpeg);
 
p1.Value = (byte[])(ms.ToArray());
//p2.Value = (byte[])b; cls.cmd.Parameters.Clear(); cls.cmd.Parameters.Add(p1);
// cls.cmd.Parameters.Add(p2);

}
}
}
 

