// change this variable to update the theme directory
var ctThemeBase = '../tree/images/index.html';

// theme node properties
var theme1 =
{
  	// tree attributes
  	//
	// except themeLevel, all other attributes can be specified
	// for each level of depth of the tree.

  	// HTML code to the left of a folder item
  	// first one is for folder closed, second one is for folder opened
	folderLeft: [['<img alt="" src="' + ctThemeBase + 'theme1/folder.gif" />', '<img alt="" src="' + ctThemeBase + 'theme1/folderopen.gif" />']],
  	// HTML code to the right of a folder item
  	// first one is for folder closed, second one is for folder opened
  	folderRight: [['', '']],
	// HTML code for the connector
	// first one is for w/ having next sibling, second one is for no next sibling
	// then inside each, the first field is for closed folder form, and the second field is for open form
	folderConnect: [[['<img alt="" src="' + ctThemeBase + 'plus.gif" />','<img alt="" src="' + ctThemeBase + 'minus.gif" />'],
					 ['<img alt="" src="' + ctThemeBase + 'plusbottom.gif" />','<img alt="" src="' + ctThemeBase + 'minusbottom.gif" />']]],

	// HTML code to the left of a regular item
	itemLeftA: ['<img alt="" src="' + ctThemeBase + 'theme1/page.gif" />'],
	itemLeftB: ['<img alt="" src="' + ctThemeBase + 'theme1/page.gif" />'],
	// HTML code to the right of a regular item
	itemRight: [''],
	// HTML code for the connector
	// first one is for w/ having next sibling, second one is for no next sibling
	itemConnect: [['<img alt="" src="' + ctThemeBase + 'join.gif" />', '<img alt="" src="' + ctThemeBase + 'joinbottom.gif" />']],

	// HTML code for spacers
	// first one connects next, second one doesn"t
	spacer: [['<img alt="" src="' + ctThemeBase + 'line.gif" />', '<img alt="" src="' + ctThemeBase + 'spacer.gif" />']],

	// deepest level of theme style sheet specified
	themeLevel: 1
};

// theme node properties
var theme2=
{
	folderLeft: [['<img alt="" src="' + ctThemeBase + 'theme2/folder.gif" />', '<img alt="" src="' + ctThemeBase + 'theme2/folderopen.gif" />']],
  	folderRight: [['<img alt="" src="' + ctThemeBase + 'theme2/add.gif" />', '<img alt="" src="' + ctThemeBase + 'theme2/add.gif" />']],
	folderConnect: [[['<img alt="" src="' + ctThemeBase + 'plus.gif" />','<img alt="" src="' + ctThemeBase + 'minus.gif" />'],
					 ['<img alt="" src="' + ctThemeBase + 'plusbottom.gif" />','<img alt="" src="' + ctThemeBase + 'minusbottom.gif" />']]],
	itemLeftA: ['<img alt="" src="' + ctThemeBase + 'theme2/page.gif" />'],
	itemRight: [''],
	itemConnect: [['<img alt="" src="' + ctThemeBase + 'join.gif" />', '<img alt="" src="' + ctThemeBase + 'joinbottom.gif" />']],

	spacer: [['<img alt="" src="' + ctThemeBase + 'line.gif" />', '<img alt="" src="' + ctThemeBase + 'spacer.gif" />']],
	themeLevel: 1
};
