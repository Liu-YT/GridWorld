/*
 * Copyright (C) 2014-2017 SonarSource SA
 * All rights reserved
 * mailto:info AT sonarsource DOT com
 */
// @flow
import React from 'react';

export default class GlobalFooterBranding extends React.Component {
  render () {
    return (
        <div>
          SonarQube&trade; technology is powered by <a href="http://www.sonarsource.com">SonarSource SA</a>
        </div>
    );
  }
}
