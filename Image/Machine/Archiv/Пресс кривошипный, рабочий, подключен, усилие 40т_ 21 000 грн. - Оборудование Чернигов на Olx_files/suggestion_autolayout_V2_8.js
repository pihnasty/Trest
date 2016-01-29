/**
 * @fileoverview Suggested Auto Layout V2_8 javascript.
 */

var elements = {
  product1MCImage: {
    type: 'background_image',
    node_id: 'adContent'
  },
  logoMCImage: {
    type: 'logo',
    node_id: 'logo-image'
  },
  text1TFText: {
    type: 'text',
    font_level: 1,
    component_type: 'headline',
    node_id: 'headline',
    font_color_id: 'text1TFTextColor',
    auto_font_color_id: 'autoText1Color',
    auto_background_color_id: 'autoBack1Color'
  },
  text2TFText: {
    type: 'text',
    font_level: 2,
    component_type: 'description',
    node_id: 'description',
    font_color_id: 'text2TFTextColor',
    auto_background_color_id: 'autoBack1Color'
  },
  clickTFText: {
    type: 'button',
    node_id: 'button',
    font_color_id: 'clickTFTextColor',
    background_color_ids: 'back1MCColor',
    auto_background_color_id: 'autoBack1Color',
    weight: 1 / 20.0
  },
  productCover: {
    type: 'shape',
    node_id: 'product-cover',
    background_color_ids: 'back1MCColor',
    auto_background_color_id: 'autoBack1Color'
  },
  verticalBorder: {
    type: 'shape',
    node_id: 'border_vertical',
    background_color_ids: 'back2MCColor',
    auto_background_color_id: 'autoBack2Color'
  },
  horizontalBorder: {
    type: 'shape',
    node_id: 'border_horizontal',
    background_color_ids: 'back2MCColor',
    auto_background_color_id: 'autoBack2Color'
  },
  line: {
    type: 'shape',
    node_id: 'line',
    background_color_ids: 'back1MCColor',
    auto_background_color_id: 'back1MCColor'
  },
  arrow: {
    type: 'shape',
    node_id: 'arrow',
    background_color_ids: 'back1MCColor',
    auto_background_color_id: 'back1MCColor'
  }
};

var smallSize_variationStyles = {
  clickTFText: {
    displayType: 'nessieButton'
  }
};

var spec = {
  elements: elements,
  font_scale_strategy: 'mega_title',
  variations: {
    tower: {
      calibrations: [
        'AspectRatioGE 0.9 0.8'
      ],
      logo: {
        type: 'element',
        spec: {
          element: 'logoMCImage'
        },
        alignments: 'top',
        top: '7%',
        bottom: '70%'
      },
      button: {
        type: 'element',
        spec: {
          element: 'clickTFText'
        },
        alignments: 'bottom',
        left: '10%',
        bottom: '15%'
      },
      headline: {
        type: 'element',
        spec: {
          element: 'text1TFText'
        },
        alignments: 'top',
        top: 'min(logo 6%, 15%)',
        bottom: '50%',
        left: '10%',
        right: '10%'
      },
      line: {
        type: 'element',
        spec: {
          element: 'line'
        },
        alignments: 'top',
        top: 'headline 0',
        left: 'headline left 0',
        right: 'headline right 0',
        bottom: 'line 2px'
      },
      description: {
        type: 'element',
        spec: {
          element: 'text2TFText'
        },
        alignments: 'top',
        top: 'headline 20px',
        bottom: 'button 6%',
        left: '10%',
        right: '10%'
      }
    },
    square: {
      logo: {
        type: 'element',
        spec: {
          element: 'logoMCImage'
        },
        alignments: 'top left',
        left: '7%',
        top: '7%',
        bottom: '70%'
      },
      button: {
        type: 'element',
        spec: {
          element: 'clickTFText'
        },
        alignments: 'bottom',
        top: 'logo',
        bottom: '15%'
      },
      headline: {
        type: 'element',
        spec: {
          element: 'text1TFText'
        },
        alignments: 'top',
        top: 'min(logo 6%, 15%)',
        bottom: '50%',
        left: '10%',
        right: '10%'
      },
      line: {
        type: 'element',
        spec: {
          element: 'line'
        },
        alignments: 'top',
        top: 'headline 0',
        left: 'headline left 0',
        right: 'headline right 0',
        bottom: 'line 2px'
      },
      description: {
        type: 'element',
        spec: {
          element: 'text2TFText'
        },
        alignments: 'top',
        top: 'headline 20px',
        bottom: 'button 6%',
        left: '10%',
        right: '10%'
      }
    },
    banner1: {
      styles: {
        text1TFText: {
          padding: '5% 0%'
        },
        text2TFText: {
          padding: '5% 0%'
        }
      },
      calibrations: [
        'AspectRatioLE 5.0 0.8'
      ],
      logo: {
        type: 'element',
        spec: {
          element: 'logoMCImage'
        },
        alignments: 'left',
        right: '50%',
        top: '10%',
        bottom: '10%'
      },
      headline: {
        type: 'element',
        spec: {
          element: 'text1TFText'
        },
        alignments: 'left bottom',
        top: '10%',
        bottom: '55%',
        left: 'min(logo 3%, 10%)',
        right: 'min(button 3%, 10%)'
      },
      line: {
        type: 'element',
        spec: {
          element: 'line'
        },
        alignments: 'top',
        top: 'headline 0',
        left: 'headline left 0',
        right: 'headline right 0',
        bottom: 'line 2px'
      },
      description: {
        type: 'element',
        spec: {
          element: 'text2TFText'
        },
        alignments: 'left top',
        top: 'headline 15px',
        bottom: '10%',
        left: 'min(logo 3%, 10%)',
        right: 'min(button 3%, 10%)'
      },
      button: {
        type: 'element',
        spec: {
          element: 'clickTFText'
        },
        alignments: 'right',
        left: '50%',
        top: '10%',
        right: '3%',
        bottom: '10%'
      }
    },
    banner2: {
      calibrations: [
        'AspectRatioLE 3.0 0.8'
      ],
      styles: {
        text1TFText: {
          padding: '5% 0%'
        },
        text2TFText: {
          padding: '5% 0%'
        }
      },
      headline: {
        type: 'element',
        spec: {
          element: 'text1TFText'
        },
        alignments: 'left bottom',
        left: '5%',
        top: '10%',
        bottom: '55%',
        right: 'min(logo, button, 15%)'
      },
      line: {
        type: 'element',
        spec: {
          element: 'line'
        },
        alignments: 'top',
        top: 'headline 0',
        left: 'headline left 0',
        right: 'headline right 0',
        bottom: 'line 2px'
      },
      description: {
        type: 'element',
        spec: {
          element: 'text2TFText'
        },
        alignments: 'left top',
        left: '5%',
        top: 'headline 15px',
        bottom: '10%',
        right: 'min(logo, button, 15%)'
      },
      logo: {
        type: 'element',
        spec: {
          element: 'logoMCImage'
        },
        alignments: 'right bottom',
        left: '40%',
        right: '5%',
        top: '20%',
        bottom: '40%'
      },
      button: {
        type: 'element',
        spec: {
          element: 'logoMCImage'
        },
        alignments: 'top right',
        left: '40%',
        top: '63%',
        bottom: '10%',
        right: '3%'
      }
    },
    banner3: {
      calibrations: [
        'AspectRatioLE 4 0'
      ],
      logo: {
        type: 'element',
        spec: {
          element: 'logoMCImage'
        },
        alignments: 'left',
        right: '70%'
      },
      text1: {
        type: 'element',
        spec: {
          element: 'text1TFText'
        },
        alignments: 'left',
        left: 'logo 4%',
        top: '10%',
        right: '50%',
        bottom: '20%'
      },
      text2: {
        type: 'element',
        spec: {
          element: 'text2TFText'
        },
        left: 'text1 4%',
        top: '10%',
        right: 'button',
        bottom: '10%'
      },
      button: {
        type: 'element',
        spec: {
          element: 'clickTFText'
        },
        alignments: 'left right',
        left: '80%'
      }
    },
    smallTower: {
      styles: smallSize_variationStyles,
      text1: {
        type: 'element',
        spec: {
          element: 'text1TFText'
        },
        bottom: 'logoAndButton 10%'
      },
      text2: {
        type: 'element',
        spec: {
          element: 'text2TFText'
        },
        bottom: 'logoAndButton 10%',
        z_index: 1
      },
      logoAndButton: {
        type: 'vertical-list',
        spec: {
          elements: ['clickTFText', 'logoMCImage'],
          margin: 'auto',
          alignments: 'bottom'
        },
        alignments: 'bottom',
        top: '60%'
      }
    },
    smallSquare: {
      styles: smallSize_variationStyles,
      text1: {
        type: 'element',
        spec: {
          element: 'text1TFText'
        },
        top: '10px',
        bottom: 'logoAndButton 10%'
      },
      text2: {
        type: 'element',
        spec: {
          element: 'text2TFText'
        },
        top: '10px',
        bottom: 'logoAndButton 10%',
        z_index: 1
      },
      logoAndButton: {
        type: 'horizontal-list',
        spec: {
          elements: ['logoMCImage', 'clickTFText'],
          margin: 'even_left',
          alignments: 'bottom'
        },
        alignments: 'bottom',
        left: '10%',
        right: '10%',
        top: '50%',
        bottom: '10px'
      }
    },
    smallBanner: {
      styles: smallSize_variationStyles,
      logo: {
        type: 'element',
        spec: {
          element: 'logoMCImage'
        },
        alignments: 'left',
        left: '3%',
        right: '50%'
      },
      text1: {
        type: 'element',
        spec: {
          element: 'text1TFText'
        },
        alignments: 'left',
        top: '2px',
        bottom: '2px',
        left: 'logo 7%',
        right: 'button'
      },
      text2: {
        type: 'element',
        spec: {
          element: 'text2TFText'
        },
        alignments: 'left',
        top: '2px',
        bottom: '2px',
        left: 'logo 7%',
        right: 'button',
        z_index: 1
      },
      button: {
        type: 'element',
        spec: {
          element: 'clickTFText'
        },
        alignments: 'right',
        left: '50%'
      }
    }
  }
};

function onAdData(adData) {
  var ccm = adData['google_template_data']['adData'][0];
  ccm['siriusFlagEnableSingleCss'] = 'true';

  renderAd(adData, spec, function() {
    decorateLayout(adData);
  });
}

function decorateLayout(adData) {
  var adContent = document.getElementById('adContent');
  var variation = adContent.getAttribute('data-variation');

  buildBorder(adData);
  buildButtonArrow(adData, variation);

  var button = document.getElementById('button');
  if (button && variation.indexOf('small') == -1) {
    button.style.backgroundColor = 'rgba(0, 0, 0, 0)';
  }

  // Adjust colors.
  var headline = document.getElementById('headline');
  var line = document.getElementById('line');
  line.style.backgroundColor = headline.style.color;
}

function buildBorder(adData) {
  // Build borders around the ad.
  var adContent = document.getElementById('adContent');
  var adWidth = parseInt(adContent.style.width);
  var adHeight = parseInt(adContent.style.height);

  var vborder = document.getElementById('border_vertical');
  var hborder = document.getElementById('border_horizontal');
  var borderWidth = Math.floor(Math.sqrt(adWidth * adHeight) * 0.02);

  vborder.style.borderColor =
      getSiriusBackgroundColor(elements['verticalBorder'], adData);
  vborder.style.borderLeftWidth = borderWidth + 'px';
  vborder.style.borderRightWidth = borderWidth + 'px';
  vborder.style.margin = borderWidth + 'px';
  vborder.style.width = adWidth - 4 * borderWidth + 'px';
  vborder.style.height = adHeight - 2 * borderWidth + 'px';
  vborder.style.backgroundColor = 'transparent';

  hborder.style.borderColor =
      getSiriusBackgroundColor(elements['horizontalBorder'], adData);
  hborder.style.borderTopWidth = borderWidth + 'px';
  hborder.style.borderBottomWidth = borderWidth + 'px';
  hborder.style.margin = borderWidth + 'px';
  hborder.style.width = adWidth - 2 * borderWidth + 'px';
  hborder.style.height = adHeight - 4 * borderWidth + 'px';
  hborder.style.backgroundColor = 'transparent';
}

function buildButtonArrow(adData, variationName) {
  var button = document.getElementById('button');
  var arrow = document.getElementById('arrow');
  arrow.innerHTML = '<svg id="arrowIcon" viewBox="0 0 17 20">' +
      '<polygon points="3,0 0,3 7,10 0,17 3,20 13,10"/></svg>';

  var variationsForSmallSize = ['smallTower', 'smallSquare', 'smallBanner'];
  var button = document.getElementById('button');
  var buttonTop = parseInt(button.style.top);
  var buttonLeft = parseInt(button.style.left);
  var buttonHeight = parseInt(button.style.height);

  if (variationsForSmallSize.indexOf(variationName) > -1 || !buttonHeight ||
      buttonLeft < buttonHeight) {
    arrow.style.display = 'none';
  }

  var arrowSize = buttonHeight * 0.6;
  arrow.style.width = arrowSize + 'px';
  arrow.style.height = arrowSize + 'px';
  arrow.style.top = (buttonTop + (buttonHeight - arrowSize) / 2) + 'px';
  arrow.style.left = (buttonLeft - arrowSize / 2) + 'px';
  arrow.style.backgroundColor = 'transparent';

  var arrowIcon = document.getElementById('arrowIcon');
  arrowIcon.style.width = '100%';
  arrowIcon.style.height = '100%';
  arrowIcon.childNodes[0].style.fill = button.style.color;
}
