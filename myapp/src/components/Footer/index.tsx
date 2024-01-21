import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import { useIntl } from 'umi';

const Footer: React.FC = () => {
  const intl = useIntl();
  const defaultMessage = intl.formatMessage({
    id: 'app.copyright.produced',
    defaultMessage: '邵龙凌出品',
  });

  const currentYear = new Date().getFullYear();

  return (
    <DefaultFooter
      copyright={`${currentYear} ${defaultMessage}`}
      links={[
        {
          key: 'Ant Design Pro',
          title: '编程导航',
          href: 'https://www.baidu.com',
          blankTarget: true,
        },
        {
          key: 'github',
          title: '邵龙凌',
          href: 'https://www.baidu.com',
          blankTarget: true,
        },
        {
          key: 'Ant Design',
          title: '张继然',
          href: 'https://www.baidu.com',
          blankTarget: true,
        },
      ]}
    />
  );
};

export default Footer;
