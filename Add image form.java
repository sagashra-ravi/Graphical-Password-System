ADD IMAGE FORM CODING

using System;
using System.Collections.Generic; using System.ComponentModel; using System.Data;
using System.Data.SqlClient; using System.Drawing;
using System.Text;
using System.Windows.Forms; namespace GraphicalPassword
{
public partial class frmAddImage : Form
{

private void button1_Click(object sender, EventArgs
e)
{
if (openFileDialog1.ShowDialog() ==
DialogResult.OK)
{
txtImage.Text = openFileDialog1.FileName; pictureBox1.Image =
Image.FromFile(txtImage.Text);
//label3.Text = "New Signature";
}
}

private void button2_Click(object sender, EventArgs
e)
{
txtImageId.Text = ""; txtImage.Text = "";
if (cls.con.State != ConnectionState.Open)
cls.con.Open();

int imageid;
cls.cmd.CommandText = "Select max(ImageId) From
Images";
 
if (cls.cmd.ExecuteScalar() == null)
{
imageid = 1;
}
else if (cls.cmd.ExecuteScalar() == DBNull.Value)
{
imageid = 1;
}
else
{
imageid = int.Parse(cls.cmd.ExecuteScalar().ToString()) + 1;

}
cls.cmd.CommandText = "insert into Images Values (" + imageid + ",@ImageData)";
txtImageId.Text = imageid.ToString(); button1.Focus();

}

private void btnSave_Click(object sender, EventArgs
e)
{
if (pictureBox1.Image == null)
{
MessageBox.Show("Please Select Signature Image.", "Alert", MessageBoxButtons.OK, MessageBoxIcon.Information);
return;
}
if (cls.con.State != ConnectionState.Open) cls.con.Open();

int imageid=int.Parse(txtImageId.Text ); string Extension=
textBox1.Text.Substring(textBox1.Text.Length -3,3);

cls.cmd.CommandText = "insert into Images Values (" + imageid + ",@ImageData,'" + Extension + "')";

SqlParameter p1 = new SqlParameter("@ImageData", SqlDbType.Image);
 
System.IO.MemoryStream ms = new System.IO.MemoryStream();

pictureBox1.Image.Save(ms, System.Drawing.Imaging.ImageFormat.Jpeg);

System.IO.File.Copy (textBox1.Text , Application.StartupPath + "\\..\\..\\audio\\" + imageid.ToString() + "." + Extension ,true);
/* System.IO.FileStream fs=new System.IO.FileStream(textBox1.Text);
byte [] b=new byte[fs.Length-1]; fs.Read (b,0,b.Length);
*/
//System.IO.MemoryStream ms2 = new System.IO.MemoryStream(b);

p1.Value = (byte[])(ms.ToArray());
//p2.Value = (byte[])b; cls.cmd.Parameters.Clear(); cls.cmd.Parameters.Add(p1);
// cls.cmd.Parameters.Add(p2);

int rec;
rec = cls.cmd.ExecuteNonQuery();

if (rec > 0)
MessageBox.Show("Image Record Saved", "Note", MessageBoxButtons.OK, MessageBoxIcon.Information);
else
MessageBox.Show("Image Record Not Saved", "Note", MessageBoxButtons.OK, MessageBoxIcon.Information);

cls.con.Close();
}

private void btnClose_Click(object sender, EventArgs e)
{
this.Close();
}
 
private void frmAddImage_Load(object sender, EventArgs e)
{
if (cls.con.State != ConnectionState.Open) cls.con.Open();

 


Images";
 
int imageid;
cls.cmd.CommandText = "Select max(ImageId) From if (cls.cmd.ExecuteScalar() == null)
 
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
 


